package com.example.jpa_bulletin_board.dto.response;
import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListDetailResponse {

    private Long postId;
    private String title;
    private String content;
    private String memberEmail;
    private String comment;


    public static PostListDetailResponse from(Post post) {
        return new PostListDetailResponse(post.getId(), post.getTitle(), post.getContent(), post.getMember().getEmail(), post.getComments().toString());
    }
}
