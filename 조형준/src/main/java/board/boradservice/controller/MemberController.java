package board.boradservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techeerpartners.TecheerPartnersBoardProject.dto.request.MemberLoginRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.request.MemberSignupRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.service.MemberService;

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
    public ResponseEntity<String> signupUser(@ModelAttribute MemberSignupRequestDTO memberSignupRequestDTO) {
        memberService.signupUser(memberSignupRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletRequest request, @ModelAttribute MemberLoginRequestDTO memberLoginRequestDTO) {
        try {
            memberService.loginUser(memberLoginRequestDTO);
            // ★★★★★★★★★★★★ memberLoginDTO니깐 memberId가 없이 이 바보놈아 이걸 햇갈리냐 진짜 ★★★★★★★★★★★
            request.getSession().setAttribute("memberEmail", memberLoginRequestDTO.getMemberEmail());
            // 로그인 성공 시
            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            // 로그인 실패 시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}









