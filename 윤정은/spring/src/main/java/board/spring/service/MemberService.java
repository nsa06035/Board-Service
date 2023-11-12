package board.spring.service;


import board.spring.domain.Member;
import board.spring.dto.request.MemberLoginRequest;
import board.spring.dto.request.MemberSaveRequest;
import board.spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;



    public void saveMember(MemberSaveRequest request) {
        Member member = request.toEntity();
        memberRepository.save(member);
    }
    public Optional<Member> findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void loginMember(MemberLoginRequest request) {
        Optional<Member> optionalMember = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (optionalMember.isPresent()) {
              Member member = optionalMember.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }



}
