package board.spring.service;


import board.boardspring.domain.Member;
import board.boardspring.dto.request.MemberLoginRequest;
import board.boardspring.dto.request.MemberSaveRequest;
import board.boardspring.repository.MemberRepository;
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
