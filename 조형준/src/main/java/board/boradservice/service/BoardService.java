package board.boradservice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techeerpartners.TecheerPartnersBoardProject.domian.Board;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;
import techeerpartners.TecheerPartnersBoardProject.dto.request.BoardSaveRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardPostResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardListResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardGetDetailResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.repository.BoardRepository;
import techeerpartners.TecheerPartnersBoardProject.repository.MemberRepository;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 게시글 생성
     */
    public void createPost(String memberEmail, BoardSaveRequestDTO boardSaveRequestDTO) {
        // 로그인 상태라면
        if (validationLogin(memberEmail)) {
            // 세션에 저장된 memberId를 이용하여 회원(Member) 객체를 찾음
            Member member = memberService.findMemberByEmail(memberEmail);

            // BoardPostDTO에 회원 객체를 설정
            BoardPostResponseDTO boardPostResponseDTO = new BoardPostResponseDTO();
            boardPostResponseDTO.setMember(member);
            boardPostResponseDTO.setBoardTitle(boardSaveRequestDTO.getBoardTitle());
            boardPostResponseDTO.setBoardContext(boardSaveRequestDTO.getBoardContext());

            Board board = boardPostResponseDTO.toBoardForSave(boardPostResponseDTO);

            boardRepository.save(board);
        } else { // 로그아웃 상태라면
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
    }

    /**
     * 게시글 수정
     */
    public void updatePost(String memberEmail, Long boardId, BoardSaveRequestDTO boardSaveRequestDTO) {
        // 로그인 상태라면
        if (validationLogin(memberEmail)) {
            // 세션에 저장된 memberId를 이용하여 회원(Member) 객체를 찾음
            Member member = memberService.findMemberByEmail(memberEmail);

            // BoardPostDTO에 회원 객체를 설정
            BoardPostResponseDTO boardPostResponseDTO = new BoardPostResponseDTO();
            boardPostResponseDTO.setMember(member);
            boardPostResponseDTO.setBoardTitle(boardSaveRequestDTO.getBoardTitle());
            boardPostResponseDTO.setBoardContext(boardSaveRequestDTO.getBoardContext());

            Board board = boardPostResponseDTO.toBoard(boardId, boardPostResponseDTO);
            if (boardRepository.existsById(boardId)) {
                boardRepository.save(board);
            } else
                throw new IllegalArgumentException("게시물이 없습니다.");
        } else {// 로그아웃 상태라면
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
    }

    /**
     * 로그인 검증
     */
    public boolean validationLogin(String memberEmail) {

        if (memberEmail != null) {
            return true;
        }
//            return boardPostRequestDTO.toBoard(boardId);
        // 예외 발생
        return false;
    }

    /**
     * 게시글 삭제
     */
    public void deletePost(HttpServletRequest request, Long boardId) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");

        if (validationLogin(memberEmail)) {
            // 게시물 존재 여부 확인
            if (boardRepository.existsById(boardId)) {
                boardRepository.deleteById(boardId);
            } else {
                throw new NoSuchElementException("존재하지 않는 게시물입니다.");
            }
        } else {
            // 로그인 상태가 아닌 경우
            throw new NoSuchElementException("로그인을 해주세요.");
        }
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardListResponseDTO> getList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardListResponseDTO> boardList = new ArrayList<>();
        for (Board board : boards) {
            boardList.add(Board.toBoardListResponseDTO(board));
        }
        return boardList;
    }
//    public List<String> showListFromTitle(String boardTitle) {
//        return boardRepository.findBoardListFromTitle(boardTitle);
//    }
//
//    public List<String> showListFromEmail(String memberEmail) {
//        return boardRepository.findBoardListFromEmail(memberEmail);
//    }

    /**
     * 게시글 상세 조회
     */
    public List<BoardGetDetailResponseDTO> getPostByTitle(String boardTitle) throws NoSuchObjectException {

        List<Board> posts = boardRepository.findByBoardTitle(boardTitle);
        if (!posts.isEmpty()) {
            List<BoardGetDetailResponseDTO> list = new ArrayList<>();
            for (Board board : posts) {
                list.add(Board.toBoardShowDetailDTO(board));
            }
            return list;
        }
        throw new NoSuchObjectException("게시글이 존재하지 않습니다.");

    }

    public List<BoardGetDetailResponseDTO> getPostByEmail(String memberEmail) throws NoSuchObjectException {
        Member member = memberRepository.findByMemberEmail(memberEmail).get();
        List<Board> posts = boardRepository.findByMember(member);
        if (!posts.isEmpty()) {
            List<BoardGetDetailResponseDTO> list = new ArrayList<>();
            for (Board board : posts) {
                list.add(Board.toBoardShowDetailDTO(board));
            }
            return list;
        }
        throw new NoSuchObjectException("게시글이 존재하지 않습니다.");
    }

    // email로 member 객체를 찾아서 return
    public Optional<Board> findPostById(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
