package board.boradservice.domian;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // DB 테이블(엔티티)에 매핑(JPA 기능)(장고 model과 같은 기능)
@Getter
//@Setter // lombok 사용(자동으로 생성해줌)
@Table(name = "comment_table")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    public Comment(final Long id, final String context, final Board board, final Member member) {
        this.id = id;
        this.context = context;
        this.board = board;
        this.member = member;
    }

    public Comment(final String context, final Board board, final Member member) {
        this.context = context;
        this.board = board;
        this.member = member;
    }

}
