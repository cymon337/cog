package com.cymon.cog.mypage.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/member-info")
    public ResponseEntity<ResponseDto> selectMemberInfo(@AuthenticationPrincipal MemberDto loginMember) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원정보 확인", mypageService.selectMemberInfo(loginMember)));
    }

    @PutMapping("/member-info/password")
    public ResponseEntity<ResponseDto> updateMemberPwd(@RequestBody HashMap<String,String> updatePwd, @AuthenticationPrincipal MemberDto loginMember) {
        String charSequencePwd = updatePwd.get("updatePwd");

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "비밀번호 변경 성공", mypageService.updateMemberPwd(charSequencePwd , loginMember)));
    }

    @PutMapping("/member-info/profile")
    public ResponseEntity<ResponseDto> updateMemberInfo(@RequestBody MemberDto updateMember, @AuthenticationPrincipal MemberDto loginMember) {
        log.info("updateMember={}",updateMember);
        loginMember.setMemberEmail(updateMember.getMemberEmail());
        loginMember.setMemberMobile(updateMember.getMemberMobile());

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원정보 변경 성공", mypageService.updateMemberInfo(loginMember)));
    }

    @PostMapping("/member-info/password")
    public ResponseEntity<ResponseDto> checkMemberPwd(@RequestBody HashMap<String,String> checkPwd, @AuthenticationPrincipal MemberDto loginMember) {
        String charSequencePwd = checkPwd.get("checkPwd");
        log.info("charSequencePwd={}", charSequencePwd);
        log.info("loginMember={}", loginMember);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "비밀번호 확인 성공", mypageService.checkMemberPwd(charSequencePwd , loginMember)));
    }

    @DeleteMapping("/member-info")
    public ResponseEntity<ResponseDto> deleteMember(@AuthenticationPrincipal MemberDto loginMember) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원탈퇴 성공", mypageService.deleteMember(loginMember)));
    }


}
