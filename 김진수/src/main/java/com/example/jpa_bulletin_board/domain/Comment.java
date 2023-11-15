package com.example.jpa_bulletin_board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
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
    private String author;

    @Column(nullable = false)
    private String comments;

    public Comment(final String comments, final String author, final Post post, final Member member) {
        this.comments = comments;
        this.author = author;
        this.post = post;
        this.member = member;
    }
}
