package board.boradservice.dto.request;


import lombok.*;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;

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
