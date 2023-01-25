package com.cymon.cog.member.service;


import com.cymon.cog.exception.UserNotFoundException;
import com.cymon.cog.member.dao.MemberMapper;
import com.cymon.cog.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper mapper;

    public CustomUserDetailsService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService] ===================================");
        log.info("[CustomUserDetailsService] loadUserByUsername {}", memberId);

        return mapper.findByMemberId(memberId)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(memberId + "> 찾을 수 없습니다."));
    }

    private MemberDto addAuthorities(MemberDto member) {
        log.info("[CustomUserDetailsService] addAuthorities MemberDto {}", member.toString());
        member.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(member.getMemberRole())));
        log.info("member autorities ={}", member.getAuthorities());
        return member;
    }

}