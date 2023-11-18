package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberLoginRequest {

    private String email;
    private String password;
}
