package board.boardspring.dto.request;

import board.boardspring.domain.Board;
import board.boardspring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class BoardSaveRequest {
    private String title;
    private String content;

    private Long memberId; // Add this field
    private Member member;

    public Board toEntity(Member member){
        return new Board(title, content, member);
    }
}
