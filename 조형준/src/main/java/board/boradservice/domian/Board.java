package board.boradservice.domian;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardListResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardGetDetailResponseDTO;

import java.util.*;

@Entity // DB 테이블(엔티티)에 매핑(JPA 기능)(장고 model과 같은 기능)
@Getter
//@Setter // lombok 사용(자동으로 생성해줌)
@Table(name = "board_table")
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContext;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //  = new ArrayList<>()를 추가하니깐 상세 조회가 작동함.
    // 그러나 Board DB 테이블에 Comment_id가 없음
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public static BoardListResponseDTO toBoardListResponseDTO(Board board) {
        return new BoardListResponseDTO(board.getBoardTitle(), board.getBoardContext(), board.getMember().getMemberEmail());
    }

    //    Entity -> DTO
    public static BoardGetDetailResponseDTO toBoardShowDetailDTO(Board board) {
        BoardGetDetailResponseDTO boardGetDetailResponseDTO = new BoardGetDetailResponseDTO();
        boardGetDetailResponseDTO.setBoardTitle(board.getBoardTitle());
        boardGetDetailResponseDTO.setBoardContext(board.getBoardContext());
        boardGetDetailResponseDTO.setMemberEmail(board.getMember().getMemberEmail());

        // Comments 리스트에서 각 Comment의 context와 member의 이름을 가져와서 맵에 저장
        Map<String, String> commentMap = new HashMap<>();
        for (Comment comment : board.getComments()) { // 여기서 에러 발생
            commentMap.put(comment.getContext(), comment.getMember().getMemberName());
        }
        boardGetDetailResponseDTO.setBoardComments(commentMap);

        return boardGetDetailResponseDTO;
    }

    // 왜 final로 선언했지?
    // 게시글 수정
    public Board(final Long id, final String boardTitle,
                final String boardContext, final Member member, final List<Comment> comments) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContext = boardContext;
        this.member = member;
        this.comments = comments;
    }

    // 게시글 작성
    public Board(final String boardTitle,
                 final String boardContext, final Member member) {
        this.boardTitle = boardTitle;
        this.boardContext = boardContext;
        this.member = member;
    }
}
