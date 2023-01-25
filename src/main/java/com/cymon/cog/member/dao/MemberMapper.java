package com.cymon.cog.member.dao;

import com.cymon.cog.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface MemberMapper {

    MemberDto selectByEmail(String memberEmail);

    MemberDto selectByMobile(String memberMobile);

    int insertMember(MemberDto member);

    Optional<MemberDto> findByMemberId(String memberId);

    MemberDto selectByMemberId(String memberId);


}
