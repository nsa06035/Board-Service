package board.boradservice.dto.response;
import board.boradservice.domian.Board;
import board.boradservice.domian.Comment;
import board.boradservice.domian.Member;
import lombok.*;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostResponseDTO {

//    private Long boardId;
    private String boardTitle;
    private String boardContext;
    // getsession을 해서 사용자 정보르 받아온 후  아래 member 객체에 넣어주면 됨
    private Member member;
    private List<Comment> comment;

    public BoardPostResponseDTO(String boardTitle, String boardContext, Member member) {
        this.boardTitle = boardTitle;
        this.boardContext = boardContext;
        this.member = member;
    }

    //    DTO -> Entity
//    public static Board toBoard(BoardPostResponseDTO boardSaveDTO) {
//        Board board = new Board();
//        board.setId(boardSaveDTO.getBoardId());
//        board.setBoardTitle(boardSaveDTO.getBoardTitle());
//        board.setBoardContext(boardSaveDTO.getBoardContext());
//        board.setMember(boardSaveDTO.getMember());
//        return board;
//    }

    public Board toBoard(Long boardId, BoardPostResponseDTO boardPostResponseDTO) {
        return new Board(boardId, boardPostResponseDTO.getBoardTitle(), boardPostResponseDTO.getBoardContext(), boardPostResponseDTO.getMember(), boardPostResponseDTO.getComment() );
    }

    public Board toBoardForSave(BoardPostResponseDTO boardPostResponseDTO) {
        return new Board(boardPostResponseDTO.getBoardTitle(), boardPostResponseDTO.getBoardContext(), boardPostResponseDTO.getMember()); }

    // Board 엔티티에 생성자를 선언해주는 방법도 있음
    /*
    public static BoardPostResponseDTO toBoard(Board board) {
        return Board(타이틀, 내용, Member);
     */
}