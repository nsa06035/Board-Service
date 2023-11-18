package board.boradservice.service;

import board.boradservice.domian.Board;
import board.boradservice.domian.Member;
import board.boradservice.dto.request.BoardSaveRequestDTO;
import board.boradservice.dto.response.BoardGetDetailResponseDTO;
import board.boradservice.dto.response.BoardListResponseDTO;
import board.boradservice.dto.response.BoardPostResponseDTO;
import board.boradservice.repository.BoardRepository;
import board.boradservice.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 게시글 생성
     */
    public void createPost(BoardSaveRequestDTO boardSaveRequestDTO) {
        Member member = memberService
                .findMemberById(boardSaveRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        BoardPostResponseDTO boardPostResponseDTO = new BoardPostResponseDTO(boardSaveRequestDTO.getBoardTitle(), boardSaveRequestDTO.getBoardContext(), member);
        // toBoardForSave부분 Mapper로 교체
        Board board = boardPostResponseDTO.toBoardForSave(boardPostResponseDTO);

        boardRepository.save(board);
    }

    /**
     * 게시글 수정
     */
    public void updatePost(Long boardId, BoardSaveRequestDTO boardSaveRequestDTO) {

        memberService
                .findMemberById(boardSaveRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        Board post = boardRepository
                .findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));

        // BoardService 클래스에 @Transactional이 있기 때문에 게시글이 최초에 저장될 때 영속성 컨텍스트 내부에 존재한다.
        // 따라서, 수정을 할 때 update() 메서드만 사용해도 영속성 컨텍스트 내부에 있는 객체가 수정되고 DB에 자동 적용된다.
        post.update(boardSaveRequestDTO);
        boardRepository.save(post);
    }

    /**
     * 게시글 삭제
     */
    public void deletePost(Long boardId) {
        Board post = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));
        boardRepository.delete(post);
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardListResponseDTO> getList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardListResponseDTO> boardList = new ArrayList<>();
        for (Board board : boards) {
            // toBoardListResponseDTO부분 Mapper로 교체
            boardList.add(Board.toBoardListResponseDTO(board));
        }
        return boardList;
    }

    /**
     * 게시글 상세 조회
     */
    public List<BoardGetDetailResponseDTO> getPostByTitle(String boardTitle) throws NoSuchObjectException {

        List<Board> posts = boardRepository
                .findByBoardTitle(boardTitle)
                .orElseThrow(() -> new NoSuchObjectException("게시물이 존재하지 않습니다."));

        List<BoardGetDetailResponseDTO> list = new ArrayList<>();
        for (Board post : posts) {
            list.add(Board.toBoardShowDetailDTO(post));
        }
        return list;
    }

    public List<BoardGetDetailResponseDTO> getPostByEmail(String memberEmail) throws NoSuchObjectException {

        Member member = memberRepository
                .findByMemberEmail(memberEmail)
                .orElseThrow(() -> new NoSuchObjectException("존재하지 않는 이메일입니다."));

        List<Board> posts = boardRepository
                .findByMember(member)
                .orElseThrow(() -> new NoSuchObjectException("게시물이 존재하지 않습니다."));

        List<BoardGetDetailResponseDTO> list = new ArrayList<>();
        for (Board post : posts) {
            list.add(Board.toBoardShowDetailDTO(post));
        }
        return list;
    }

    // email로 member 객체를 찾아서 return
    public Optional<Board> findPostById(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
