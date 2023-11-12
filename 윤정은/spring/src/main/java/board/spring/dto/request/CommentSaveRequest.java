package board.spring.dto.request;

import board.spring.domain.Board;
import board.spring.domain.Comment;
import board.spring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Setter
public class CommentSaveRequest {

    private String content;
    private Long memberId;
    private Long boardId;


    private Member member;
    private Board board;
    // Add setters for member and boardId
    public void setMember(Member member) {
        this.memberId = member.getId();
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Comment toEntity(Member member, Board board) {
        return new Comment(content, member, board);
    }
}
