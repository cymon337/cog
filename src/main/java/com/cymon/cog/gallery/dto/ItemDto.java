package com.cymon.cog.gallery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String itemNo;
    private Date itemRegDate;
    private String itemStatus;
    private MultipartFile tImg;
    private String tImgPath;
    private MultipartFile fImg;
    private String fImgPath;
    private MultipartFile bImg;
    private String bImgPath;
    private MultipartFile dImg;
    private String dImgPath;
}
