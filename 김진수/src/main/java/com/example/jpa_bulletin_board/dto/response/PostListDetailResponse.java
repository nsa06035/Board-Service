package com.example.jpa_bulletin_board.dto.response;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
public class PostListDetailResponse {

    private Long postId;
    private String title;
    private String content;
    private String memberEmail;
    private String comment;

    public static PostListDetailResponse from(Post post) {
        return new PostListDetailResponse(post.getId(), post.getTitle(), post.getContent(), post.getMember().getEmail(), post.getComments().toString());
    }

    public PostListDetailResponse() {
        // 기본 생성자
    }

    public PostListDetailResponse(Long postId, String title, String content, String memberEmail, String comment) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.memberEmail = memberEmail;
        this.comment = comment;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
