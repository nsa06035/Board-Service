package board.spring.dto.request;

import board.spring.domain.Board;
import board.spring.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class BoardSaveRequest {
    private String title;
    private String content;

    private Long memberId;
    private Member member;

    public Board toEntity(Member member){
        return new Board(title, content, member);
    }
}
