package board.spring.service;


import board.spring.domain.Board;
import board.spring.domain.Comment;
import board.spring.domain.Member;
import board.spring.dto.request.CommentSaveRequest;
import board.spring.repository.BoardRepository;
import board.spring.repository.CommentRepository;
import board.spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public void savePost(CommentSaveRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid memberId"));

        // Use the boardId from the request
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        Comment comment = request.toEntity(member, board);
        commentRepository.save(comment);
    }

    public Optional<Comment> findCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public void updateComment(Comment existingComment, String newContent) {
        Comment updatedComment = new Comment(newContent);
        existingComment.updateContent(newContent);
        commentRepository.save(updatedComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

