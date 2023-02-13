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


        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("lastItem", lastItem);
        responseMap.put("registResult", result);

        return responseMap;
    }

    @Transactional
    public Object updateItems(ItemDto updateItem) {

        int result = galleryManagerMapper.updateItemById(updateItem);

        ItemDto foundItem = galleryManagerMapper.findItemById(updateItem);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("updateItem", foundItem);
        responseMap.put("updateResult", result);

        return responseMap;
    }


    public Object deleteItems(ItemDto deleteItem) {

        ItemDto foundItem = galleryManagerMapper.findItemById(deleteItem);
        if (foundItem == null) {
            throw new GalleryManagerException("아이템이 존재하지 않습니다!");
        }

        int result = galleryManagerMapper.deleteItems(deleteItem);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("deleteItem", foundItem);
        responseMap.put("deleteResult", result);

        return responseMap;

    }

}
