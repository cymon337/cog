package com.cymon.cog.gallery.service;

import com.cymon.cog.gallery.dao.GalleryMapper;
import com.cymon.cog.gallery.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryMapper galleryMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    public List<ItemDto> selectItems() {

        log.debug("selectItems start =====================");

        List<ItemDto> itemList = galleryMapper.selectItems();
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).setTImgPath(IMAGE_URL + itemList.get(i).getTImgPath());
        }

        return itemList;
    }


}
