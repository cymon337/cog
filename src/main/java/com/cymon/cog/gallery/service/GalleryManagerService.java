package com.cymon.cog.gallery.service;

import com.cymon.cog.gallery.dao.GalleryManagerMapper;
import com.cymon.cog.gallery.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryManagerService {

    private final GalleryManagerMapper galleryManagerMapper;

    @Value("/itemsImg/")
    private String IMAGE_DIR;
    @Value("http://localhost:8090/itemsImg/")
    private String IMAGE_URL;


    public List<ItemDto> selectItems() {

        log.debug("selectItems start =====================");

        List<ItemDto> itemList = galleryManagerMapper.selectItems();
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).setTImgPath(IMAGE_URL + itemList.get(i).getTImgPath());
        }

        return itemList;
    }

//    @Transactional
//    public Object registItems(ItemDto registItem) {
//
//        int result = galleryManagerMapper.registItems(registItem);
//
//        ItemDto lastItem = galleryManagerMapper.findLatestItem();
//
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("lastItem", lastItem);
//        responseMap.put("registResult", result);
//
//        return responseMap;
//    }
//
//    @Transactional
//    public Object updateItems(ItemDto updateItem) {
//
//        int result = galleryManagerMapper.updateItemById(updateItem);
//
//        ItemDto foundItem = galleryManagerMapper.findItemById(updateItem);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("updateItem", foundItem);
//        responseMap.put("updateResult", result);
//
//        return responseMap;
//    }
//
//
//    public Object deleteItems(ItemDto deleteItem) {
//
//        ItemDto foundItem = galleryManagerMapper.findItemById(deleteItem);
//        if (foundItem == null) {
//            throw new GalleryManagerException("아이템이 존재하지 않습니다!");
//        }
//
//        int result = galleryManagerMapper.deleteItems(deleteItem);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("deleteItem", foundItem);
//        responseMap.put("deleteResult", result);
//
//        return responseMap;
//
//    }

}
