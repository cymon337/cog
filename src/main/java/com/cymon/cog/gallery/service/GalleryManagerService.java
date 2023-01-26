package com.cymon.cog.gallery.service;

import com.cymon.cog.exception.GalleryManagerException;
import com.cymon.cog.gallery.dao.GalleryManagerMapper;
import com.cymon.cog.gallery.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryManagerService {

    private final GalleryManagerMapper galleryManagerMapper;

    public List<ItemDto> selectItems() {

        log.debug("selectItems start =====================");

        List<ItemDto> itemList = galleryManagerMapper.selectItems();

        return itemList;
    }


    @Transactional
    public Object registItems(ItemDto registItem) {

        int result = galleryManagerMapper.registItems(registItem);

        ItemDto lastItem = galleryManagerMapper.findLatestItem();
        log.info("==== registItem.getItemLocation() ={}", registItem.getItemLocation());
        log.info("==== galleryManagerMapper.findLatestItem() ={}", lastItem.getItemLocation());

        if (!Objects.equals(lastItem.getItemLocation(), registItem.getItemLocation())) {
            throw new GalleryManagerException("관리자 아이템 등록 에러, 추가한 아이템의 itemLocation 이 최신항목과 일치하지 않습니다!");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("lastItem", lastItem);
        responseMap.put("registResult", result);

        return responseMap;
    }



}
