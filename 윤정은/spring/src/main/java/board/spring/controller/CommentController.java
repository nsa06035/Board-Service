package board.spring.controller;


import board.spring.domain.Comment;
import board.spring.domain.Member;
import board.spring.dto.request.CommentSaveRequest;
import board.spring.service.CommentService;
import board.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Void> createComment(
            @RequestParam Long boardId,
            @RequestParam Long memberId,
            @RequestBody CommentSaveRequest request) {

        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();

            if (boardId != null) {
                request.setMember(loggedInMember);
                request.setBoardId(boardId);
                commentService.savePost(request);

                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 댓글 수정
    @PutMapping
    public ResponseEntity<Void> updateComment(
            @RequestParam Long commentId,
            @RequestBody CommentSaveRequest request) {

        Optional<Comment> optionalComment = commentService.findCommentById(commentId);

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            String newContent = request.getContent();
            commentService.updateComment(existingComment, newContent);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // 댓글 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestParam Long commentId) {
        Optional<Comment> optionalComment = commentService.findCommentById(commentId);

        if (optionalComment.isPresent()) {
            // Add logic to check if the logged-in member is the author of the comment (for authorization)

            commentService.deleteComment(commentId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}








