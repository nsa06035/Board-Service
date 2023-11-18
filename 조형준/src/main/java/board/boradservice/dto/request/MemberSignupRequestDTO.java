package board.boradservice.dto.request;


import board.boradservice.domian.Member;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberSignupRequestDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public Member toMember() {
        return new Member(id, memberEmail, memberPassword, memberName);
    }
}
