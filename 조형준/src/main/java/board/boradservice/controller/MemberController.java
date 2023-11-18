package board.boradservice.controller;

import board.boradservice.dto.request.MemberLoginRequestDTO;
import board.boradservice.dto.request.MemberSignupRequestDTO;
import board.boradservice.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

// @RestController : Json 반환
// @Controller : view 반환
// 따라서 -> 프론트 없이 백 API만 만든다면 @RestController 사용
@RestController
@RequiredArgsConstructor
// 컨트롤러 클래스 단위에 @RequestMapping("/api/boards")를 설정하면, 해당 컨트롤러 클래스의 모든 메서드에 기본적으로 /api/boards가 적용됨
@RequestMapping("/api/members")
class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody MemberSignupRequestDTO memberSignupRequestDTO) {
        memberService.signupUser(memberSignupRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
        memberService.loginUser(memberLoginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    // ------------------------- ExceptionHandler -------------------------
    /**
     * IllegalArgumentException을 처리하는 메서드
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * NoSuchElementException을 처리하는 메서드
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}