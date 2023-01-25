package com.cymon.cog.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

public class MemberDto implements UserDetails {

    private String memberId;
    private String memberPwd;
    private String memberEmail;
    private String memberMobile;
    private String memberRole;
    private Date memberRegDate;
    private String memberStatus;


    // constructor
    public MemberDto() {
    }

    public MemberDto(String memberId, String memberPwd, String memberEmail, String memberMobile, String memberRole, Date memberRegDate, String memberStatus, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberEmail = memberEmail;
        this.memberMobile = memberMobile;
        this.memberRole = memberRole;
        this.memberRegDate = memberRegDate;
        this.memberStatus = memberStatus;
        this.authorities = authorities;
    }

    // getter, setter
    public String getMemberId() {return memberId;}

    public void setMemberId(String memberId) {this.memberId = memberId;}

    public String getMemberPwd() {return memberPwd;}

    public void setMemberPwd(String memberPwd) {this.memberPwd = memberPwd;}

    public String getMemberEmail() {return memberEmail;}

    public void setMemberEmail(String memberEmail) {this.memberEmail = memberEmail;}

    public String getMemberMobile() {return memberMobile;}

    public void setMemberMobile(String memberMobile) {this.memberMobile = memberMobile;}

    public String getMemberRole() {return memberRole;}

    public void setMemberRole(String memberRole) {this.memberRole = memberRole;}

    public Date getMemberRegDate() {return memberRegDate;}

    public void setMemberRegDate(Date memberRegDate) {this.memberRegDate = memberRegDate;}

    public String getMemberStatus() {return memberStatus;}

    public void setMemberStatus(String memberStatus) {this.memberStatus = memberStatus;}

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    // security code
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberMobile='" + memberMobile + '\'' +
                ", memberRole='" + memberRole + '\'' +
                ", memberRegDate=" + memberRegDate +
                ", memberStatus='" + memberStatus + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
