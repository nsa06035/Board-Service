package board.spring.dto.request;
// 회원가입 (post) : 이메일과 비밀번호, 닉네임을 입력 받아 회원가입을 한다.


import board.boardspring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class MemberSaveRequest {

    private String email;
    private String password;
    private String nickname;

    public Member toEntity(){
        return new Member(email,password,nickname);
    }

}
