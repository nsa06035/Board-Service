package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberSaveRequest {

    private String email;
    private String password;
    private String nickname;

    public Member toEntity() {
        return new Member(email, password, nickname);
    }
}
