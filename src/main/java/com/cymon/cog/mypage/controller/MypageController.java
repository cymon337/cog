package com.cymon.cog.mypage.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @PutMapping("/member-info/password")
    public ResponseEntity<ResponseDto> updateMemberPwd(@RequestBody String updatePwd, @AuthenticationPrincipal MemberDto loginMember) {
        String loginId = loginMember.getMemberId();
        log.info("updateMemberPwd loginId={}", loginId);
        log.info("updateMemberPwd updatePwd={}", updatePwd);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "비밀번호 변경 성공", mypageService.updateMemberPwd(updatePwd , loginId)));
    }



}
