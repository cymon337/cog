package com.cymon.cog.mypage.service;

import com.cymon.cog.exception.CollectionException;
import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.dao.CollectionMapper;
import com.cymon.cog.mypage.dto.CollectionDto;
import com.cymon.cog.mypage.dto.CollectionViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionMapper collectionMapper;

    public Object selectCollection(MemberDto loginMember) {

        List<CollectionViewDto> collectionViewList = collectionMapper.selectCollection(loginMember);

        if (collectionViewList == null) {
            throw new CollectionException("컬렉션에 아이템이 존재하지 않습니다!");
        }

        return collectionViewList;

    }

    public Object registCollection(HashMap<String, String> registItem, MemberDto loginMember) {

        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setMemberId(loginMember.getMemberId());
        collectionDto.setItemNo(registItem.get("itemNo"));

        if (collectionMapper.selectCollectionByIdAndItemNo(collectionDto) != null) {
            throw new CollectionException(collectionDto.getMemberId() +" 회원이 이미 등록한 아이템입니다!");
        }

        int result = collectionMapper.registCollection(collectionDto);

        if (result == 0) {
            throw new CollectionException(" 아이템 등록 실패!");
        }

        List<CollectionViewDto> collectionViewList = collectionMapper.selectCollection(loginMember);

        CollectionViewDto foundItem = collectionViewList.stream()
                .filter(item -> Objects.equals(item.getItemNo(), collectionDto.getItemNo()))
                .collect(Collectors.toList()).get(0);


        return foundItem;
    }

    public Object deleteCollection(HashMap<String, String> deleteItem, MemberDto loginMember) {

        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setMemberId(loginMember.getMemberId());
        collectionDto.setItemNo(deleteItem.get("itemNo"));

        if (collectionMapper.selectCollectionByIdAndItemNo(collectionDto) == null) {
            throw new CollectionException("아이템이 컬렉션에 존재하지 않습니다.");
        }

        int result = collectionMapper.deleteCollection(collectionDto);

        if (result == 0) {
            throw new CollectionException(" 아이템 삭제 실패!");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberId", collectionDto.getMemberId());
        responseMap.put("itemNo", collectionDto.getItemNo());
        responseMap.put("deleteResult", result);

        return responseMap;
    }
}
