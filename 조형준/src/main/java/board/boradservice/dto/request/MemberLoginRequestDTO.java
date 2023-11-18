package board.boradservice.dto.request;

import board.boradservice.domian.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequestDTO {
//    private Long id;
    private String memberEmail;
    private String memberPassword;

    public Member toMember(MemberLoginRequestDTO memberLoginRequestDTO) {
        return new Member(memberEmail, memberPassword);
    }
}
