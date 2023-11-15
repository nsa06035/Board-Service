package board.boradservice.dto.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGetDetailResponseDTO {

    private String boardTitle;
    private String boardContext;
    private String memberEmail;
    private Map<String, String> boardComments;
//    private List<String> boardComment;
//    private List<String> boardCommentName;

    // Board 엔티티에 생성자를 선언해주는 방법도 있음
    /*
    public static BoardPostResponseDTO toBoard(Board board) {
        return Board(타이틀, 내용, Member);
     */
}