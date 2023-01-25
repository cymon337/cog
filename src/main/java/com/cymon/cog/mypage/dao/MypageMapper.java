package com.cymon.cog.mypage.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {


    int updateMemberPwd(String updatePwd,String loginId);
}
