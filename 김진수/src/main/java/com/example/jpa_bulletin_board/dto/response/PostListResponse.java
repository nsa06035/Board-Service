package com.example.jpa_bulletin_board.dto.response;

import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
public class PostListResponse {

    private String title;
    private String content;

    public static PostListResponse from(Post post) {
        return new PostListResponse(post.getTitle(), post.getContent());
    }

    public PostListResponse() {
        // 기본 생성자
    }

    public PostListResponse(String title, String content) {
        this.title = title;
        this.content = content;
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
}
