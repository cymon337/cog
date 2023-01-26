package com.cymon.cog.gallery.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.gallery.service.GalleryService;
import com.cymon.cog.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/gallery-manager")
@RequiredArgsConstructor
public class GalleryManagerController {

    private final GalleryService galleryService;

    @GetMapping("/items")
    public ResponseEntity<ResponseDto> selectItems(@AuthenticationPrincipal MemberDto admin) {


        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "갤러리 조회 성공", galleryService.selectItems()));
    }




}
