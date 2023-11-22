package com.example.jpa_bulletin_board.controller;

import com.example.jpa_bulletin_board.dto.request.CommentSaveRequest;
import com.example.jpa_bulletin_board.dto.request.CommentUpdateRequest;
import com.example.jpa_bulletin_board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    // 생성자 주입 사용
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // postId, memberId를 dto로 받으면 어떨까?
    // memberId가 DB에 없는 상황이라면 throw로 예외를 발생시키는게 맞는지?
    // 아니면
    /**
     * 댓글 작성
     */
    @PostMapping
    public ResponseEntity<String> saveComment(
            @RequestBody CommentSaveRequest commentSaveRequest) {
        if (commentService.saveComment(commentSaveRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("회원이 존재하지 않거나 게시글이 존재하지 않습니다.");
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest) {
        if (commentService.updateComment(commentUpdateRequest, commentId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("댓글이 존재하지 않습니다.");
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("commentId") Long commentId) {
        if (commentService.deleteComment(commentId)) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("댓글이 존재하지 않습니다.");
    }
}
