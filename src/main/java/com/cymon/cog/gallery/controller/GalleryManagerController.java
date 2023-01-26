package com.cymon.cog.gallery.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.gallery.dto.ItemDto;
import com.cymon.cog.gallery.service.GalleryManagerService;
import com.cymon.cog.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/gallery-manager")
@RequiredArgsConstructor
public class GalleryManagerController {

    private final GalleryManagerService galleryManagerService;

    @GetMapping("/items")
    public ResponseEntity<ResponseDto> selectItems(@AuthenticationPrincipal MemberDto admin) {


        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() +" 관리자 갤러리 조회 성공 ", galleryManagerService.selectItems()));
    }

    @PostMapping("/items")
    public ResponseEntity<ResponseDto> registItems(@RequestBody ItemDto registItem, @AuthenticationPrincipal MemberDto admin) {
        log.info("registItem ={}", registItem);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() +" 관리자 갤러리 아이템 등록 성공 ", galleryManagerService.registItems(registItem)));
    }


}
