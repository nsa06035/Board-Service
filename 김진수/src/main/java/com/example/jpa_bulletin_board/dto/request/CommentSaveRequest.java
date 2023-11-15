package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Comment;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommentSaveRequest {
    private String comments;
    private String author;

    public Comment toEntity(Member member, Post post) {
        return new Comment(comments, author, post, member);
    }
}
