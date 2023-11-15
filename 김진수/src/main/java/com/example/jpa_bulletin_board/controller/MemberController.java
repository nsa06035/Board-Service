package com.example.jpa_bulletin_board.controller;

import com.example.jpa_bulletin_board.dto.request.MemberLoginRequest;
import com.example.jpa_bulletin_board.dto.request.MemberSaveRequest;
import com.example.jpa_bulletin_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping
    public ResponseEntity<Void> saveMember(MemberSaveRequest memberSaveRequest) {
        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public String login(MemberLoginRequest memberLoginRequest) {
        String email = memberLoginRequest.getEmail();
        String password = memberLoginRequest.getPassword();

        if(memberService.login(email, password)) {
            return "로그인 성공!";
        }
        return "로그인 실패..";
    }

}
