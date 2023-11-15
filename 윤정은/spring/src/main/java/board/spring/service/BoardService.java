package board.spring.service;

import board.spring.domain.Board;
import board.spring.domain.Comment;
import board.spring.domain.Member;
import board.spring.dto.request.BoardSaveRequest;
import board.spring.dto.response.BoardDetailResponse;
import board.spring.dto.response.BoardListResponse;
import board.spring.repository.BoardRepository;
import board.spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 저장
    public void savePost(Long memberId,BoardSaveRequest request) {
        Optional<Member> existingMember = memberRepository.findById(memberId);
        Member member = existingMember.orElseThrow(() -> new IllegalArgumentException("Invalid"));
        Board board = request.toEntity(member);
        boardRepository.save(board);
    }

    // 게시글 목록 조회
    public List<BoardListResponse> findBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }

    // 게시글 제목으로 게시글 목록 검색
    public List<BoardListResponse> findBoardListByTitle(String title) {
        List<Board> boardList = boardRepository.findAllByTitleStartingWith(title);

        return boardList.stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }

    // 특정 회원이 작성한 게시글 조회
    public ResponseEntity<List<BoardListResponse>> findPostListByEmail(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();

            List<Board> postList = boardRepository.findAllListByMemberId(findMember.getId());

            List<BoardListResponse> responseList = postList.stream()
                    .map(BoardListResponse::from)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }


    // 게시글 상세 조회 (제목, 내용, 회원이름, 댓글)을 포함
    public List<BoardDetailResponse> findDetailList(Long boardId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);

        return boardOptional.map(board -> Collections.singletonList(BoardDetailResponse.from(board)))
                .orElse(Collections.emptyList());
    }


    // 게시물 수정
    public void updateBoard(Long boardId, BoardSaveRequest request) {
        Optional<Board> existingComment = boardRepository.findById(boardId);
        Board board = existingComment.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "board not found"));
        board.update(request);
        boardRepository.save(board);
    }


    // 게시글 삭제
    public void deletePost(Long boardId) {
        Optional<Board> existingComment = boardRepository.findById(boardId);
        Board existingBoard = boardRepository.findById(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
        boardRepository.delete(existingBoard);
    }
}

