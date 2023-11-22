package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
public class MemberSaveRequest {

    private String email;
    private String password;
    private String nickname;

    public Member toEntity() {
        return new Member(email, password, nickname);
    }

    public MemberSaveRequest() {
        // 기본 생성자
    }

    public MemberSaveRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
