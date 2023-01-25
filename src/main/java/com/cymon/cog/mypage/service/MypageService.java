package com.cymon.cog.mypage.service;

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

    private final MypageMapper mypageMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Object updateMemberPwd(MemberDto memberDto) {
        log.info("[MypageService] updateMemberPwd Start ===================================");
        log.info("[MypageService] MemberRequestDto {}", memberDto);


    }
}
