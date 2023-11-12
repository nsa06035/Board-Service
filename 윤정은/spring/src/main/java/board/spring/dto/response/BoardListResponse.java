package board.spring.dto.response;

import board.boardspring.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponse {
    private String title;
    private String content;

    public static BoardListResponse from(Board board){
        return new BoardListResponse(board.getTitle(), board.getContent());
    }



}

