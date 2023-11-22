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
//@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 생성자 주입을 사용
    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    //memberId dto로 넣어서 전달해보기
    //postId dto로 넣어서 전달해보기
    /**
     * 댓글 작성
     */
    public boolean saveComment(CommentSaveRequest commentSaveRequest) {
        Long memberId = commentSaveRequest.getMemberId();
        Long postId = commentSaveRequest.getPostId();

        Optional<Post> findPost = postRepository.findById(postId);
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findPost.isPresent()) {
            Post post = findPost.get();
            Member member = findMember.get();
            Comment comment = commentSaveRequest.toEntity(member, post);
            //comment.setPost(post);
            //comment.setMember(member);
            commentRepository.save(comment);
            return true;
        } else {
//            throw new IllegalArgumentException("ID에 해당하는 댓글 찾을 수 없습니다." + postId);
            return false;
        }
    }

    // updateRequest를 따로 만들어야함
    // 수정인데 save 하는게 이상해 보일 수 있음
    // 수정 삭제는 204(No Content)로 보내야함 -> 찾아보기
    // save 없어도 수정 가능함

    //commentId dto로 넣어서 보내보기
    /**
     * 댓글 수정
     */
    public boolean updateComment(CommentUpdateRequest commentUpdateRequest, Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if (findComment.isPresent()) {
            Comment comment = findComment.get();
            comment.setComments(commentUpdateRequest.getComments());
//            commentRepository.save(comment);
            return true;
        }
//        throw new IllegalArgumentException("ID에 해당하는 댓글을 찾을 수 없습니다." + commentId);
        return false;
    }

    /**
     * 댓글 삭제
     */
    public boolean deleteComment(Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if (findComment.isPresent()) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }
}
