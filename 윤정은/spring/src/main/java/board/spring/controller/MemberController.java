package board.spring.controller;

import board.boardspring.dto.request.MemberLoginRequest;
import board.boardspring.dto.request.MemberSaveRequest;
import board.boardspring.service.MemberService;
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

    // 회원가입 (post) : 이메일과 비밀번호, 닉네임을 입력 받아 회원가입을 한다.
    @PostMapping
    public ResponseEntity<Void> saveMember(@RequestBody MemberSaveRequest request){
        memberService.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    // 로그인 (post) : 이메일과 비밀번호를 입력 받아 로그인을 한다.
    @PostMapping("/login")
    public ResponseEntity<Void> loginMember(@RequestBody MemberLoginRequest request) {
        try {
            memberService.loginMember(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // Handle login failure, you can customize the response based on your requirements
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
