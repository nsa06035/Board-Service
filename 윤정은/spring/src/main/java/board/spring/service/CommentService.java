package board.spring.service;


import board.spring.domain.Board;
import board.spring.domain.Comment;
import board.spring.domain.Member;
import board.spring.dto.request.CommentSaveRequest;
import board.spring.dto.request.CommentUpdateRequest;
import board.spring.repository.BoardRepository;
import board.spring.repository.CommentRepository;
import board.spring.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    public void saveComment(Long memberId,Long boardId,CommentSaveRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Member ID"));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Board ID"));

        Comment newComment = request.toEntity(member,board);
        commentRepository.save(newComment);
    }


    // 댓글 수정
    public void updateComment(CommentUpdateRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        Comment updatedComment = request.toEntity();
//        commentRepository.save(updatedComment);
    }


    // 댓글 삭제 : 응답값 추가하기
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
        commentRepository.deleteById(commentId);
    }


}

