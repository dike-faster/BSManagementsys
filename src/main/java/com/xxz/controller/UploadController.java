
package com.xxz.controller;


import com.xxz.Pojo.Result;
import com.xxz.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
        public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传{}",image);
        AliOSSUtils aliOSSUtils = new AliOSSUtils();
        return Result.success(aliOSSUtils.upload(image));
    }
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//        //获取原始文件名   ->拓展名
//        String originalFilename = image.getOriginalFilename();
//
//        //构造唯一的文件名(不能重复)--uuid(通用唯一识别码) aa7325bd-333e-45f9-979d-f8e7d707390d
//        int index = originalFilename.lastIndexOf(".");
//        String extname=originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名{}",newFileName );
//
//        //将文件存储在服务器的磁盘目录中 D:\image
//        image.transferTo(new File("D:\\images\\"+ newFileName));
//
//        return Result.success();
//    }
}
