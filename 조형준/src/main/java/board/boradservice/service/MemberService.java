package board.boradservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;
import techeerpartners.TecheerPartnersBoardProject.dto.request.MemberLoginRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.request.MemberSignupRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.repository.MemberRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void signupUser(MemberSignupRequestDTO memberSignupRequestDTO) {
//        이미 존재하는 이메일인지 검증
        validateDuplicateMember(memberSignupRequestDTO);

        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
//        Member member = MemberSignupRequestDTO.toMember(memberSignupRequestDTO);
        Member member = memberSignupRequestDTO.toMember();
        memberRepository.save(member);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public void loginUser(MemberLoginRequestDTO memberLoginRequestDTO) {

        // 이메일 존재하는지 확인
        Optional<Member> memberOptional = memberRepository.findByMemberEmail(memberLoginRequestDTO.getMemberEmail());

        // 이메일이 존재한다면 비밀번호가 일치하는지 확인
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            String memberPassword = member.getMemberPassword();
            String inputPassword = memberLoginRequestDTO.getMemberPassword();

            // 비밀번호가 틀린 경우
            if (!memberPassword.equals(inputPassword)) {
                throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
            }
        }else {
            // 이메일이 존재하지 않는 경우
            throw new NoSuchElementException("아이디를 찾을 수 없습니다.");
        }
    }

    // 중복 회원 검증 로직
    private void validateDuplicateMember(MemberSignupRequestDTO memberSignupRequestDTO) {
        Optional<Member> findMembersEmails = memberRepository.findByMemberEmail(memberSignupRequestDTO.getMemberEmail());
        if (findMembersEmails.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // memberId로 member 객체를 찾아서 return
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public Member findMemberByEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail).get();
    }


}