package board.spring.dto.request;

import board.spring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(email,password);
    }
}
