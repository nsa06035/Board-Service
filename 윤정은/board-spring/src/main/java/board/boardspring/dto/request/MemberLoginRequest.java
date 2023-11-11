package board.boardspring.dto.request;

import board.boardspring.domain.Member;
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
