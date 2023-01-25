package com.cymon.cog.mypage.service;

import com.cymon.cog.member.dao.MemberMapper;
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
    public Object updateMemberPwd(String updatePwd , String loginId) {
        log.info("updateMemberPwd Start ===================================");
        String encodePwd = passwordEncoder.encode(updatePwd);
        log.info("updatePwd ={}", updatePwd);
        log.info("encodePwd ={}", encodePwd);

        boolean test = passwordEncoder.matches(updatePwd, encodePwd);
        log.info("test ={}", test);
        int result = mypageMapper.updateMemberPwd(encodePwd ,loginId);



        return "memberId = " + loginId + ", result = " + result;


    }
}
