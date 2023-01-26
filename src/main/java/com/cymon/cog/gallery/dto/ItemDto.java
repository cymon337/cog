package com.cymon.cog.gallery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String itemNo;
    private String itemLocation;
    private Date itemRegDate;
    private String itemStatus;
}
