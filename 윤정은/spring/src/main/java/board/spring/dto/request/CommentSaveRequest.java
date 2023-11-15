package board.spring.dto.request;

import board.spring.domain.Board;
import board.spring.domain.Comment;
import board.spring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;


@AllArgsConstructor
@Getter @Setter
public class CommentSaveRequest {

    private String content;

    private Member member;
    private Board board;

    public Comment toEntity(Member member, Board board) {
        return new Comment(content, member, board);
    }
}
