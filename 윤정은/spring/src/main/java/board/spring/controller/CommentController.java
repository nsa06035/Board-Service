package board.spring.controller;

import board.spring.dto.request.CommentSaveRequest;
import board.spring.dto.request.CommentUpdateRequest;
import board.spring.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    // POST /api/comments
    @PostMapping
    public ResponseEntity<Void> createComment(
            @Valid @RequestParam Long memberId,
            @Valid @RequestParam Long boardId,
            @RequestBody CommentSaveRequest request) {

        commentService.saveComment(memberId, boardId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 댓글 수정
    // PUT /api/comments/1
    @PutMapping("{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request) {
        commentService.updateComment(request, commentId);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    // DELETE /api/comments/1
    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}








