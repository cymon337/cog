package com.cymon.cog.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDto {

private String cltNo;
private String memberId;
private String itemNo;
private Date cltRegDate;


}
