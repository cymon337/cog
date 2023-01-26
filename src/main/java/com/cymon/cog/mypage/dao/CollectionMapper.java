package com.cymon.cog.mypage.dao;

import com.cymon.cog.member.dto.MemberDto;
import com.cymon.cog.mypage.dto.CollectionDto;
import com.cymon.cog.mypage.dto.CollectionViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    List<CollectionViewDto> selectCollection(MemberDto loginMember);

    int registCollection(CollectionDto collectionDto);

    CollectionDto selectCollectionByIdAndItemNo(CollectionDto collectionDto);

    int deleteCollection(CollectionDto collectionDto);
}
