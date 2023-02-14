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


        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() + " 관리자 갤러리 조회 성공 ", galleryManagerService.selectItems()));
    }

    @GetMapping("/items/nextItemNo")
    public ResponseEntity<ResponseDto> selectNextItemNo(@AuthenticationPrincipal MemberDto admin) {
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() + " nextItemNo 조회 성공 ", galleryManagerService.selectNextItemNo()));
    }


    @PostMapping("/items")
    public ResponseEntity<ResponseDto> registItems(@ModelAttribute ItemDto registItem, @AuthenticationPrincipal MemberDto admin) {
        log.info("registItem ={}", registItem);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() +" 관리자 갤러리 아이템 등록 성공 ", galleryManagerService.registItems(registItem)));
    }

//    @PutMapping("/items")
//    public ResponseEntity<ResponseDto> updateItems(@RequestBody ItemDto updateItem, @AuthenticationPrincipal MemberDto admin) {
//        log.info("updateItems ={}", updateItem);
//
//        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() +" 관리자 갤러리 아이템 수정 성공 ", galleryManagerService.updateItems(updateItem)));
//    }
//
//    @DeleteMapping("/items")
//    public ResponseEntity<ResponseDto> deleteItems(@RequestBody ItemDto deleteItem, @AuthenticationPrincipal MemberDto admin) {
//        log.info("deleteItem ={}", deleteItem);
//
//        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, admin.getMemberId() +" 관리자 갤러리 아이템 삭제 성공 ", galleryManagerService.deleteItems(deleteItem)));
//    }


}
