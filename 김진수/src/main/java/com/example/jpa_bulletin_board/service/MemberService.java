package com.example.jpa_bulletin_board.service;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.dto.request.MemberLoginRequest;
import com.example.jpa_bulletin_board.dto.request.MemberSaveRequest;
import com.example.jpa_bulletin_board.exception.DuplicateEmailException;
import com.example.jpa_bulletin_board.exception.DuplicateNickNameException;
import com.example.jpa_bulletin_board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
//@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 주입 사용
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public boolean saveMember(MemberSaveRequest memberSaveRequest) {
        if (isEmailUnique(memberSaveRequest.getEmail())) {
            if(isNicknameUnique(memberSaveRequest.getNickname())) {
                Member member = memberSaveRequest.toEntity();
                memberRepository.save(member);
                return true;
            }
//            throw new DuplicateNickNameException("이미 존재하는 닉네임입니다.");
        }
//        throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        return false;
    }

    private boolean isEmailUnique(String email) {
        return memberRepository.findByEmail(email) == null;
    }

    private boolean isNicknameUnique(String nickname) {
        return memberRepository.findByNickname(nickname) == null;
    }

    /**
     * 로그인
     */
    public boolean login(MemberLoginRequest memberLoginRequest) {
        String email = memberLoginRequest.getEmail();
        String password = memberLoginRequest.getPassword();

        //입력한 이메일에 해당하는 회원이 존재하는지 확인
        Member member = memberRepository.findByEmail(email);

        if(member!= null) {
            //입력한 비밀번호와 회원의 비밀번호가 일치하는지 확인
            return password.equals(member.getPassword());
        }

        return false; //회원이 존재하지 않음
    }


}
