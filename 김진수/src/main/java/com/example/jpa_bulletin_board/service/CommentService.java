package com.example.jpa_bulletin_board.service;

import com.example.jpa_bulletin_board.domain.Comment;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import com.example.jpa_bulletin_board.dto.request.CommentSaveRequest;
import com.example.jpa_bulletin_board.dto.request.CommentUpdateRequest;
import com.example.jpa_bulletin_board.repository.CommentRepository;
import com.example.jpa_bulletin_board.repository.MemberRepository;
import com.example.jpa_bulletin_board.repository.PostRepository;
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
    private final PostRepository postRepository;

    /**
     * 댓글 작성
     */
    public void saveComment(CommentSaveRequest commentSaveRequest, Long postId, Long memberId) {
        Optional<Post> findPost = postRepository.findById(postId);
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findPost.isPresent()) {
            Post post = findPost.get();
            Member member = findMember.get();
            Comment comment = commentSaveRequest.toEntity(member, post);
            //comment.setPost(post);
            //comment.setMember(member);
            commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("ID에 해당하는 댓글 찾을 수 없습니다." + postId);
        }
    }

    /**
     * 댓글 수정
     */
    // updateRequest를 따로 만들어야함
    // 수정인데 save 하는게 이상해 보일 수 있음
    // 수정 삭제는 204(No Content)로 보내야함 -> 찾아보기
    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if (findComment.isPresent()) {
            Comment comment = findComment.get();
            comment.setComments(commentUpdateRequest.getComments());
            commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("ID에 해당하는 댓글을 찾을 수 없습니다." + commentId);
        }
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
