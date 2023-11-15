package board.boradservice.dto.response;
import lombok.Getter;
import lombok.Setter;
import techeerpartners.TecheerPartnersBoardProject.domian.Board;
import techeerpartners.TecheerPartnersBoardProject.domian.Comment;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;

@Getter
@Setter
public class CommentCreateResponseDTO {

    private Long id;
    private String commentContext;
    private Board board;
    private Member member;

    //    DTO -> Entity
    public Comment toComment(Long commentId, CommentCreateResponseDTO commentCreateResponseDTO) {
        return new Comment(commentId, commentCreateResponseDTO.getCommentContext(), commentCreateResponseDTO.getBoard(), commentCreateResponseDTO.getMember());
    }

    public Comment toCommentForSave(CommentCreateResponseDTO commentCreateResponseDTO) {
        return new Comment(commentCreateResponseDTO.getCommentContext(), commentCreateResponseDTO.getBoard(), commentCreateResponseDTO.getMember());
    }
}
