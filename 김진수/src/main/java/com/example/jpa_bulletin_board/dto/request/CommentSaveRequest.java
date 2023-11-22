package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Comment;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter @Setter
public class CommentSaveRequest {
    private String comments;
    private Long memberId;
    private Long postId;

    public Comment toEntity(Member member, Post post) {
        return new Comment(comments, post, member);
    }

    public CommentSaveRequest(String comments, Long memberId, Long postId) {
        this.comments = comments;
        this.memberId = memberId;
        this.postId = postId;
    }

    public CommentSaveRequest() {
        //기본 생성자
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}