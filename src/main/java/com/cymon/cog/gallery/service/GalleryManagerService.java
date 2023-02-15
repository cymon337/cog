package com.cymon.cog.gallery.service;

import com.cymon.cog.gallery.dao.GalleryManagerMapper;
import com.cymon.cog.gallery.dto.ItemDto;
import com.cymon.cog.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryManagerService {

    private final GalleryManagerMapper galleryManagerMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;


    public List<ItemDto> selectItems() {

        log.debug("selectItems start =====================");

        List<ItemDto> itemList = galleryManagerMapper.selectItems();
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).setTImgPath(IMAGE_URL + itemList.get(i).getTImgPath());
        }

        return itemList;
    }

    public Object selectNextItemNo() {
        int nextItemNo = galleryManagerMapper.selectNextItemNo();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("nextItemNo", nextItemNo);

        return responseMap;
    }

    @Transactional
    public String registItems(ItemDto registItem) {
        log.debug("registItems start =====================");

        // UUID 부여
        String imageNameT = UUID.randomUUID().toString().replace("-", "");
        String imageNameF = UUID.randomUUID().toString().replace("-", "");
        String imageNameB = UUID.randomUUID().toString().replace("-", "");
        String imageNameD = UUID.randomUUID().toString().replace("-", "");
        String replaceFileNameTImg = null;
        String replaceFileNameFImg = null;
        String replaceFileNameBImg = null;
        String replaceFileNameDImg = null;
        int result = 0;

        log.info("[GalleryManagerService] IMAGE_DIR : " + IMAGE_DIR);
        log.info("[GalleryManagerService] imageName : " + imageNameT);
        log.info("[GalleryManagerService] imageName : " + imageNameF);
        log.info("[GalleryManagerService] imageName : " + imageNameB);
        log.info("[GalleryManagerService] imageName : " + imageNameD);

        try {
            // t img
            log.info("[GalleryManagerService] T IMG =====================");
            replaceFileNameTImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameT, registItem.getTImg());
            log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameTImg);
            registItem.setTImgPath(replaceFileNameTImg);
            log.info("[GalleryManagerService] insert Image Name : "+ replaceFileNameTImg);

            // f img
            log.info("[GalleryManagerService] F IMG =====================");
            replaceFileNameFImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameF, registItem.getFImg());
            log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameFImg);
            registItem.setFImgPath(replaceFileNameFImg);
            log.info("[GalleryManagerService] insert Image Name : "+ replaceFileNameFImg);

            // b img
            log.info("[GalleryManagerService] B IMG =====================");
            replaceFileNameBImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameB, registItem.getBImg());
            log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameBImg);
            registItem.setBImgPath(replaceFileNameBImg);
            log.info("[GalleryManagerService] insert Image Name : "+ replaceFileNameBImg);

            // d img
            log.info("[GalleryManagerService] D IMG =====================");
            replaceFileNameDImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameD, registItem.getDImg());
            log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameDImg);
            registItem.setDImgPath(replaceFileNameDImg);
            log.info("[GalleryManagerService] insert Image Name : "+ replaceFileNameDImg);

            log.info(registItem.toString());

            result = galleryManagerMapper.registItems();
            if(result == 1) {
                result = galleryManagerMapper.registImagesT(registItem);
                result += galleryManagerMapper.registImagesF(registItem);
                result += galleryManagerMapper.registImagesB(registItem);
                result += galleryManagerMapper.registImagesD(registItem);
            }

        } catch (IOException e) {
            // t img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameTImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameTImg);

            // f img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameFImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameFImg);

            // b img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameBImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameBImg);

            // d img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameDImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameDImg);

            throw new RuntimeException(e);
        }
        log.info("[GalleryManagerService] result > 0 성공: "+ result);
        return (result > 0) ? "아이템 등록 성공 "+result+" 개 이미지 등록" : "아이템 등록 실패";
    }

    @Transactional
    public String updateItems(ItemDto updateItem) {
        log.debug("updateItems start =====================");

        // UUID 부여
        String imageNameT = UUID.randomUUID().toString().replace("-", "");
        String imageNameF = UUID.randomUUID().toString().replace("-", "");
        String imageNameB = UUID.randomUUID().toString().replace("-", "");
        String imageNameD = UUID.randomUUID().toString().replace("-", "");
        String replaceFileNameTImg = null;
        String replaceFileNameFImg = null;
        String replaceFileNameBImg = null;
        String replaceFileNameDImg = null;
        int result = 0;

        log.info("[GalleryManagerService] IMAGE_DIR : " + IMAGE_DIR);
        log.info("[GalleryManagerService] imageName : " + imageNameT);
        log.info("[GalleryManagerService] imageName : " + imageNameF);
        log.info("[GalleryManagerService] imageName : " + imageNameB);
        log.info("[GalleryManagerService] imageName : " + imageNameD);

        try {
            // t img
            if(updateItem.getTImg() != null) {
                log.info("[GalleryManagerService] T IMG =====================");
                replaceFileNameTImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameT, updateItem.getTImg());
                log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameTImg);
                updateItem.setTImgPath(replaceFileNameTImg);
                log.info("[GalleryManagerService] insert Image Name : "+ replaceFileNameTImg);
            }

            // f img
            if(updateItem.getFImg() != null) {
                log.info("[GalleryManagerService] F IMG =====================");
                replaceFileNameFImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameF, updateItem.getFImg());
                log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameFImg);
                updateItem.setFImgPath(replaceFileNameFImg);
                log.info("[GalleryManagerService] insert Image Name : " + replaceFileNameFImg);
            }
            // b img
            if(updateItem.getBImg() != null) {
                log.info("[GalleryManagerService] B IMG =====================");
                replaceFileNameBImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameB, updateItem.getBImg());
                log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameBImg);
                updateItem.setBImgPath(replaceFileNameBImg);
                log.info("[GalleryManagerService] insert Image Name : " + replaceFileNameBImg);
            }
            // d img
            if(updateItem.getDImg() != null) {
                log.info("[GalleryManagerService] D IMG =====================");
                replaceFileNameDImg = FileUploadUtils.saveFile(IMAGE_DIR, imageNameD, updateItem.getDImg());
                log.info("[GalleryManagerService] replaceFileName : " + replaceFileNameDImg);
                updateItem.setDImgPath(replaceFileNameDImg);
                log.info("[GalleryManagerService] insert Image Name : " + replaceFileNameDImg);
            }

            log.info(updateItem.toString());

            if(updateItem.getItemStatus() != null){
                log.info(updateItem.getItemStatus());
                result += galleryManagerMapper.updateItem(updateItem);
            }
            if(updateItem.getTImg() != null) result += galleryManagerMapper.updateImagesT(updateItem);
            if(updateItem.getFImg() != null) result += galleryManagerMapper.updateImagesF(updateItem);
            if(updateItem.getBImg() != null) result += galleryManagerMapper.updateImagesB(updateItem);
            if(updateItem.getDImg() != null) result += galleryManagerMapper.updateImagesD(updateItem);


        } catch (IOException e) {
            // t img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameTImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameTImg);

            // f img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameFImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameFImg);

            // b img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameBImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameBImg);

            // d img
            log.info("[GalleryManagerService] IOException IMAGE_DIR : "+ IMAGE_DIR);
            log.info("[GalleryManagerService] IOException deleteFile : "+ replaceFileNameDImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileNameDImg);

            throw new RuntimeException(e);
        }
        log.info("[GalleryManagerService] result > 0 성공: "+ result);
        return (result > 0) ? "아이템 변경 성공 "+result+" 개 아이템 정보 변경" : "아이템 변경 없음";
    }


//
//    @Transactional
//    public Object updateItems(ItemDto updateItem) {
//
//        int result = galleryManagerMapper.updateItemById(updateItem);
//
//        ItemDto foundItem = galleryManagerMapper.findItemById(updateItem);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("updateItem", foundItem);
//        responseMap.put("updateResult", result);
//
//        return responseMap;
//    }
//
//
//    public Object deleteItems(ItemDto deleteItem) {
//
//        ItemDto foundItem = galleryManagerMapper.findItemById(deleteItem);
//        if (foundItem == null) {
//            throw new GalleryManagerException("아이템이 존재하지 않습니다!");
//        }
//
//        int result = galleryManagerMapper.deleteItems(deleteItem);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("deleteItem", foundItem);
//        responseMap.put("deleteResult", result);
//
//        return responseMap;
//
//    }

}
