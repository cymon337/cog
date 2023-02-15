package com.cymon.cog.gallery.dao;

import com.cymon.cog.gallery.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryMapper {
    List<ItemDto> selectItems();

    ItemDto selectItemInfo(String itemNo);

}
