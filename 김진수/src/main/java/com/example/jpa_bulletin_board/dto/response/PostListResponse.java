package com.example.jpa_bulletin_board.dto.response;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListResponse {

    private String title;
    private String content;

    public static PostListResponse from(Post post) {
        return new PostListResponse(post.getTitle(), post.getContent());
    }
}
