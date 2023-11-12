package board.spring.service;

import board.boardspring.domain.Board;
import board.boardspring.domain.Member;
import board.boardspring.dto.request.BoardSaveRequest;
import board.boardspring.dto.response.BoardDetailResponse;
import board.boardspring.dto.response.BoardListResponse;
import board.boardspring.repository.BoardRepository;
import board.boardspring.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void savePost(BoardSaveRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Board board = request.toEntity(member);
        boardRepository.save(board);
    }

    public List<BoardListResponse> findBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }

    public List<BoardListResponse> findBoardListByTitle(String title) {
        List<Board> boardList = boardRepository.findAllByTitleStartingWith(title);

        return boardList.stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }

    //특정 회원이 작성한 게시글 조회
    public List<BoardListResponse> findPostListByEmail(String email) {
        Member findMember = memberRepository.findByEmail(email);

        List<Board> postList = boardRepository.findAllListByMemberId(findMember.getId());

        return postList.stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }


    // 상세 조회
    public BoardDetailResponse findDetailList(Long boardId, String email) {
        Member findMember = memberRepository.findByEmail(email);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));

        String memberName = (findMember != null) ? findMember.getEmail() : "Unknown";
        return new BoardDetailResponse(board.getTitle(), board.getContent(), memberName, board.getComments());
    }

    // 게시물 수정
    public void updatePost(Long boardId, BoardSaveRequest request) {
        Board existingBoard = findBoardById(boardId).orElseThrow(() -> new NotFoundException("Board not found"));

        // Update the board post with the new information
        existingBoard.setTitle(request.getTitle());
        existingBoard.setContent(request.getContent());

        boardRepository.save(existingBoard);
    }

    public Optional<Board> findBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    // 게시글 삭제
    public void deletePost(Long boardId) {
        // Assuming you have the method to find the board by ID
        Board existingBoard = findBoardById(boardId).orElseThrow(() -> new NotFoundException("Board not found"));

        boardRepository.delete(existingBoard);
    }


}

