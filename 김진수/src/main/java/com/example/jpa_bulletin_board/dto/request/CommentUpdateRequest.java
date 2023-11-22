package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Comment;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class CommentUpdateRequest {
    private String comments;

    public Comment toEntity(Member member, Post post) {
        return new Comment(comments, post, member);
    }

    public CommentUpdateRequest() {
        //기본 생성자
    }
    
    public CommentUpdateRequest(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
