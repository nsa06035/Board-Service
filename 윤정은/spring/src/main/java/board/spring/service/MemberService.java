package board.spring.service;

import board.spring.domain.Member;
import board.spring.dto.request.MemberLoginRequest;
import board.spring.dto.request.MemberSaveRequest;
import board.spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public boolean loginMember(MemberLoginRequest request) {
        return memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .isPresent();
    }
}
