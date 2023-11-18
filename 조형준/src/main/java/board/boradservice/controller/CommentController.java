package board.boradservice.controller;

import board.boradservice.dto.request.CommentCreateRequestDTO;
import board.boradservice.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

// @RestController : Json 반환
// @Controller : view 반환
// 따라서 -> 프론트 없이 백 API만 만든다면 @RestController 사용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 작성
     */
    @PostMapping("/")
    public ResponseEntity<String> createComment(@ModelAttribute CommentCreateRequestDTO commentCreateRequestDTO) {

        commentService.createComment(commentCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 작성 성공");
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentCreateRequestDTO commentCreateRequestDTO) {

        commentService.updateComment(commentId, commentCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정 성공");
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestParam Long boardId, Long memberId) {

        commentService.deleteComment(boardId, commentId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 성공");
    }

    //------------------------------------------------------------------------------------------------------------------

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