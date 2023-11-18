package board.spring.controller;

import board.spring.dto.request.BoardSaveRequest;
import board.spring.dto.response.BoardDetailResponse;
import board.spring.dto.response.BoardListResponse;
import board.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<Void> saveBoard(@RequestBody BoardSaveRequest request) {
        boardService.savePost(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 전체 게시글 목록 조회
    @GetMapping
    public List<BoardListResponse> findBoardList() {
        List<BoardListResponse> responseList = boardService.findBoardList();
        return responseList;
    }

    // 게시글 제목으로 게시글 목록을 검색
    @GetMapping("/search")
    public List<BoardListResponse> findBoardListByTitle(@RequestParam String title) {
        List<BoardListResponse> responseList = boardService.findBoardListByTitle(title);
        return responseList;
    }

    // 특정 회원이 작성한 게시글 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BoardListResponse>> findBoardListByEmail(@PathVariable Long memberId) {
        ResponseEntity<List<BoardListResponse>> responseEntity = boardService.findPostListByEmail(memberId);
        return responseEntity;
    }


    // 게시글 상세 조회 (제목, 내용, 회원이름, 댓글)을 포함
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponse> findBoardDetail(@PathVariable Long boardId) {
        return boardService.findDetail(boardId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardSaveRequest request) {
        boardService.updateBoard(boardId,request);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deletePost(boardId);
        return ResponseEntity.ok().build();
    }
}