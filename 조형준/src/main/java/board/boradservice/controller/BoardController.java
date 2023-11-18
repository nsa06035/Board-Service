package board.boradservice.controller;

import board.boradservice.dto.request.BoardSaveRequestDTO;
import board.boradservice.dto.response.BoardGetDetailResponseDTO;
import board.boradservice.dto.response.BoardListResponseDTO;
import board.boradservice.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    // 생성자 주입
    private final BoardService boardService;

    /**
     * 게시글 생성
     */
    @PostMapping("/")
    public ResponseEntity<String> createPost(@RequestBody BoardSaveRequestDTO boardSaveRequestDTO) {
        boardService.createPost(boardSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물 작성 성공");

    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<String> updatePost(@PathVariable Long boardId, @RequestBody BoardSaveRequestDTO boardSaveRequestDTO) {
        boardService.updatePost(boardId, boardSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 수정 성공");
    }

    /**
     * 게시글 삭제
     * 게시글 삭제를 위해서는 boardId만 있으면 되므로 인자로 boardId만 받는다.
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deletePost(@PathVariable Long boardId) {
        boardService.deletePost(boardId);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제 성공");

    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardListResponseDTO>> getAllPosts() {
        // 게시물이 없는 경우 빈 리스트가 나오도록 설정(예외처리 굳이?)
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getList());
    }

    /**
     * 게시글 상세 조회
     * @PathVariable로 교체
     */
    @GetMapping("/boardTitle/{boardTitle}")
    public ResponseEntity<List<BoardGetDetailResponseDTO>> getPostByTitle(@PathVariable String boardTitle) throws NoSuchObjectException {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getPostByTitle(boardTitle));

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * IllegalArgumentException을 처리하는 메서드
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * NoSuchElementException을 처리하는 메서드
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * NoSuchObjectException을 처리하는 메서드 (예외 타입이 잘못되었다면 예시 기반으로 수정 필요)
     */
    @ExceptionHandler(NoSuchObjectException.class)
    public ResponseEntity<String> handleNoSuchObjectException(NoSuchObjectException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }
}
