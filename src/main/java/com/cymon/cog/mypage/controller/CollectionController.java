package com.cymon.cog.mypage.controller;

import com.cymon.cog.common.ResponseDto;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Slf4j
@RestController
@RequestMapping("/api/v1/mypage/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping("/items")
    public ResponseEntity<ResponseDto> selectCollection(@AuthenticationPrincipal MemberDto loginMember) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "컬렉션 조회 성공", collectionService.selectCollection(loginMember)));
    }

    @PostMapping("/items")
    public ResponseEntity<ResponseDto> registCollection(@RequestBody HashMap<String, String> registItem, @AuthenticationPrincipal MemberDto loginMember) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "컬렉션에 아이템 등록 성공", collectionService.registCollection(registItem, loginMember)));
    }

    @DeleteMapping("/items")
    public ResponseEntity<ResponseDto> deleteCollection(@RequestBody HashMap<String, String> deleteItem, @AuthenticationPrincipal MemberDto loginMember) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "컬렉션에 아이템 삭제 성공", collectionService.deleteCollection(deleteItem, loginMember)));
    }





}












