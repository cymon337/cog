package com.cymon.cog.member.dto;


public class TokenDto {
    private String grantType;
    private String memberId;
    private String accessToken;
    private Long accessTokenExpiresIn;

    public TokenDto() {
    }

    public TokenDto(String grantType, String memberId, String accessToken, Long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "grantType='" + grantType + '\'' +
                ", memberId='" + memberId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiresIn=" + accessTokenExpiresIn +
                '}';
    }
}