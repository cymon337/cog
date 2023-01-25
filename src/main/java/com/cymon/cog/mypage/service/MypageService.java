package com.cymon.cog.mypage.service;

import com.cymon.cog.exception.LoginFailedException;
import com.cymon.cog.member.dao.MemberMapper;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.dao.MypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {

    private final MemberMapper memberMapper;
    private final MypageMapper mypageMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Object updateMemberPwd(MemberDto memberDto) {
        log.info("[MypageService] updateMemberPwd Start ===================================");
        log.info("[MypageService] MemberRequestDto {}", memberDto);

        // 1. 아이디 조회
        MemberDto member = memberMapper.findByMemberId(memberDto.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));

        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(memberDto.getMemberPwd(), member.getMemberPwd())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
        }

        return memberDto;


    }
}
