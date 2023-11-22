package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
public class MemberLoginRequest {

    private String email;
    private String password;

    public MemberLoginRequest() {
        // 기본 생성자
    }

    public MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
}
