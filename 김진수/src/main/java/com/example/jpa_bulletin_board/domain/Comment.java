package com.example.jpa_bulletin_board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter @Setter
//@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "commentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member; //댓글 작성 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post; // 게시글

    @Column(nullable = false)
    private String comments;

    public Comment(final String comments, final Post post, final Member member) {
        this.comments = comments;
        this.post = post;
        this.member = member;
    }

    // 기본 생성자
    public Comment() {
    }

    // id의 Getter
    public Long getId() {
        return id;
    }

    // id의 Setter
    public void setId(Long id) {
        this.id = id;
    }

    // member의 Getter
    public Member getMember() {
        return member;
    }

    // member의 Setter
    public void setMember(Member member) {
        this.member = member;
    }

    // post의 Getter
    public Post getPost() {
        return post;
    }

    // post의 Setter
    public void setPost(Post post) {
        this.post = post;
    }

    // comments의 Getter
    public String getComments() {
        return comments;
    }

    // comments의 Setter
    public void setComments(String comments) {
        this.comments = comments;
    }
}
