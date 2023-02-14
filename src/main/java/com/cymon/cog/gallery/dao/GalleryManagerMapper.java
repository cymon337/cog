package com.cymon.cog.gallery.dao;

import com.cymon.cog.gallery.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryManagerMapper {
    List<ItemDto> selectItems();

    int selectNextItemNo();
    int registItems();
    int registImagesT(ItemDto registItem);
    int registImagesF(ItemDto registItem);
    int registImagesB(ItemDto registItem);
    int registImagesD(ItemDto registItem);



//
//    ItemDto findLatestItem();
//
//    int updateItemById(ItemDto updateItem);
//
//    ItemDto findItemById(ItemDto updateItem);
//
//    int deleteItems(ItemDto deleteItem);
}
