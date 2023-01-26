package com.cymon.cog.mypage.dao;

import com.cymon.cog.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {


    int updateMemberPwd(MemberDto loginMember);

    int updateMemberInfo(MemberDto updateMember);

    int deleteMember(MemberDto loginMember);
}
