package board.boradservice.dto.response;
import board.boradservice.domian.Board;
import board.boradservice.domian.Comment;
import board.boradservice.domian.Member;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentCreateResponseDTO {

    private Long id;
    private String commentContext;
    private Board board;
    private Member member;

    /**
     * 댓글 생성
     */
    public CommentCreateResponseDTO(String commentContext, Board board, Member member) {
        this.commentContext = commentContext;
        this.board = board;
        this.member = member;
    }

    /**
     * 댓글 수정
     */
    public CommentCreateResponseDTO(Long id, String commentContext, Board board, Member member) {
        this.commentContext = commentContext;
        this.board = board;
        this.member = member;
    }

    //    DTO -> Entity
    public Comment toComment(Long commentId, CommentCreateResponseDTO commentCreateResponseDTO) {
        return new Comment(commentId, commentCreateResponseDTO.getCommentContext(), commentCreateResponseDTO.getBoard(), commentCreateResponseDTO.getMember());
    }

    public Comment toCommentForSave(CommentCreateResponseDTO commentCreateResponseDTO) {
        return new Comment(commentCreateResponseDTO.getCommentContext(), commentCreateResponseDTO.getBoard(), commentCreateResponseDTO.getMember());
    }
}
