package board.boradservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techeerpartners.TecheerPartnersBoardProject.dto.request.CommentCreateRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.service.CommentService;

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
    public ResponseEntity<String> createComment(HttpServletRequest request, @ModelAttribute CommentCreateRequestDTO commentCreateRequestDTO) {

        try {
            commentService.createComment(request, commentCreateRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("댓글 작성 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(HttpServletRequest request, @PathVariable Long commentId, @ModelAttribute CommentCreateRequestDTO commentCreateRequestDTO) {

        try {
            commentService.updateComment(request, commentId, commentCreateRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("댓글 수정 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(HttpServletRequest request, @PathVariable Long commentId, @RequestParam Long boardId) {

        try {
            commentService.deleteComment(request, boardId, commentId);
            return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}