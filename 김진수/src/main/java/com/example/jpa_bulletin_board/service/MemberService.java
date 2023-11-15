package com.example.jpa_bulletin_board.service;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.dto.request.MemberLoginRequest;
import com.example.jpa_bulletin_board.dto.request.MemberSaveRequest;
import com.example.jpa_bulletin_board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        Member member = memberSaveRequest.toEntity();
        memberRepository.save(member);
    }

    /**
     * 로그인
     */
    public boolean login(String email, String password) {
        Member findMember = memberRepository.findByEmail(email);

        return findMember != null && findMember.getPassword().equals(password);
    }


}
