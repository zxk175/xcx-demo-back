package com.zxk175.xcx.module.controller;

import com.zxk175.oss.entity.OssModel;
import com.zxk175.oss.service.BaseOssService;
import com.zxk175.xcx.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author zxk175
 * @since 2020-03-18 10:02
 */
@Controller
@AllArgsConstructor
@RequestMapping("/file")
@Api(tags = "File 微信小程序")
public class FileController {

    private BaseOssService ossService;


    @ResponseBody
    @PostMapping("/upload/v1")
    @ApiOperation(value = "Oss文件上传", notes = "Oss文件上传")
    public Response<?> authLogin(MultipartFile file) throws Exception {
        System.out.println(ossService.getOssName());
        System.out.println(ossService.getProperties().toString());
        OssModel ossModel = ossService.uploadSuffix(file.getInputStream(), "oss/test/", "json");
        return Response.success(ossModel);
    }

}