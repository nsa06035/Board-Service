package com.example.jpa_bulletin_board.controller;

import com.example.jpa_bulletin_board.dto.request.MemberLoginRequest;
import com.example.jpa_bulletin_board.dto.request.MemberSaveRequest;
import com.example.jpa_bulletin_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping
    public ResponseEntity<String> saveMember(
            @RequestBody MemberSaveRequest memberSaveRequest) {
        if (memberService.saveMember(memberSaveRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("이미 존재하는 이메일이거나 이미 존재하는 닉네임입니다.");
    }

    //return을 string으로 주는게 맞나?
    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody MemberLoginRequest memberLoginRequest) {
        if (memberService.login(memberLoginRequest)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("이메일 혹은 비밀번호를 확인해주세요.");
    }

}
