package com.cymon.cog.gallery.dao;

import com.cymon.cog.gallery.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryManagerMapper {
    List<ItemDto> selectItems();

    int selectNextItemNo();
// 아이템 등록 맵퍼
    int registItems();
    int registImagesT(ItemDto registItem);
    int registImagesF(ItemDto registItem);
    int registImagesB(ItemDto registItem);
    int registImagesD(ItemDto registItem);
// 아이템 변경 맵퍼
    int updateItem(ItemDto updateItem);

    int updateImagesT(ItemDto updateItem);

    int updateImagesF(ItemDto updateItem);

    int updateImagesB(ItemDto updateItem);

    int updateImagesD(ItemDto updateItem);


//
//    ItemDto findLatestItem();
//
//    int updateItemById(ItemDto updateItem);
//
//    ItemDto findItemById(ItemDto updateItem);
//
//    int deleteItems(ItemDto deleteItem);
}
