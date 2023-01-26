package com.cymon.cog.mypage.service;

import com.cymon.cog.exception.EmailInputException;
import com.cymon.cog.exception.LoginFailedException;
import com.cymon.cog.exception.MobileInputException;
import com.cymon.cog.exception.PasswordInputException;
import com.cymon.cog.member.dao.MemberMapper;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.dao.MypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {

    private final MemberMapper memberMapper;
    private final MypageMapper mypageMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Object updateMemberPwd(String updatePwd , MemberDto loginMember) {
        log.info("updateMemberPwd Start ===================================");
        log.info("updatePwd = {}", updatePwd);

        // 입력된 변경할 비밀번호 예외처리
        if (updatePwd == null) {
            throw new PasswordInputException("잘못된 비밀번호 입력입니다.");
        }

        // 로그인 회원 MemberDto 에 변경할 비밀번호 set
        loginMember.setMemberPwd(passwordEncoder.encode(updatePwd));

        int result = mypageMapper.updateMemberPwd(loginMember);

        // 변경 후 회원정보 ID로 조회
        MemberDto member = memberMapper.findByMemberId(loginMember.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberId", member.getMemberId());
        responseMap.put("updatePwd", updatePwd);
        responseMap.put("passwordEncoderMatches", passwordEncoder.matches(updatePwd ,member.getMemberPwd()));
        responseMap.put("updateResult", result);

        return responseMap;


    }

    @Transactional
    public Object updateMemberInfo(MemberDto updateMember) {

        // 예외처리 : 이메일, 모바일 중복 조회 및 입력값 확인 ( todo 정규표현식 테스트)
        MemberDto inputEmail = memberMapper.selectByEmail(updateMember.getMemberEmail());
        MemberDto inputMobile = memberMapper.selectByMobile(updateMember.getMemberMobile());
        if (inputEmail != null) {
            throw new EmailInputException("사용중인 이메일 입니다.");
        } else if (inputMobile != null) {
            throw new MobileInputException("사용중인 전화번호 입니다.");
        }
        // 이메일, 모바일 업데이트
        int result = mypageMapper.updateMemberInfo(updateMember);
        // 변경 후 회원정보 ID로 조회
        MemberDto member = memberMapper.findByMemberId(updateMember.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));
        // 결과메세지 처리



        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberId", member.getMemberId());
        responseMap.put("updateEmail", member.getMemberEmail());
        responseMap.put("updateMobile", member.getMemberMobile());
        responseMap.put("updateResult", result);

        return responseMap;
    }

    @Transactional
    public Object checkMemberPwd(String charSequencePwd, MemberDto loginMember) {
        // 회원정보 ID로 조회
        MemberDto member = memberMapper.findByMemberId(loginMember.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));
        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(charSequencePwd ,member.getMemberPwd())) {
            throw new PasswordInputException("비밀번호가 일지하지 않습니다.");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberId", member.getMemberId());
        responseMap.put("checkPwd", charSequencePwd);

        return responseMap;

    }

    public Object deleteMember(MemberDto loginMember) {
        int result = mypageMapper.deleteMember(loginMember);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberId", loginMember.getMemberId());
        responseMap.put("deleteResult", result);

        return responseMap;
    }
}
