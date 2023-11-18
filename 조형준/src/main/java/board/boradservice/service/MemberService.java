package board.boradservice.service;


import board.boradservice.domian.Member;
import board.boradservice.dto.request.MemberLoginRequestDTO;
import board.boradservice.dto.request.MemberSignupRequestDTO;
import board.boradservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public void signupUser(MemberSignupRequestDTO memberSignupRequestDTO) {
        memberRepository.findByMemberEmail(memberSignupRequestDTO.getMemberEmail())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
                });
        Member member = memberSignupRequestDTO.toMember();
        memberRepository.save(member);
    }

    /**
     * 로그인
     */
    public void loginUser(MemberLoginRequestDTO memberLoginRequestDTO) {
        Member memberOptional = memberRepository
                .findByMemberEmailAndPassword(memberLoginRequestDTO.getMemberEmail(), memberLoginRequestDTO.getMemberPassword())
                .orElseThrow(() -> new NoSuchElementException("이메일 또는 비밀번호가 잘못 되었습니다."));
    }

    // memberId로 member 객체를 찾아서 return
    public Optional<Member> findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findMemberByEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail).get();
    }


}