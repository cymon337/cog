package com.cymon.cog.gallery.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/items")
    public ResponseEntity<ResponseDto> selectItems() {


        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "갤러리 조회 성공", galleryService.selectItems()));
    }




}
