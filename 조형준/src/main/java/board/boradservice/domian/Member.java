package board.boradservice.domian;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
//@Setter
@Getter
@Table(name = "member_table")
@NoArgsConstructor
public class Member {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true, nullable = false) // unique 제약조건 추가
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberName;

    /**
     * 회원가입에 사용
     */
    public Member(final Long id, final String memberEmail, final String memberPassword, final String memberName) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }

    /**
     * 로그인에 사용
     */
    public Member(final String memberEmail, final String memberPassword) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Member member = (Member) o;
//        return Objects.equals(id, member.id);
//    }
//
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
