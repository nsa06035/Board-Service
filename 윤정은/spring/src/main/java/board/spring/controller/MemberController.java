package board.spring.controller;

import board.spring.dto.request.MemberLoginRequest;
import board.spring.dto.request.MemberSaveRequest;
import board.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    // POST api/members
    @PostMapping
    public ResponseEntity<Void> saveMember(@RequestBody MemberSaveRequest request){
        memberService.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인
    // POST api/members/login
    @PostMapping("/login")
    public ResponseEntity<Void> loginMember(@RequestBody MemberLoginRequest request) {
        memberService.loginMember(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
