package board.spring.dto.request;


import board.spring.domain.Comment;

public class CommentUpdateRequest {
    private String content;

    public Comment toEntity() {
        return new Comment(content);
    }
}
