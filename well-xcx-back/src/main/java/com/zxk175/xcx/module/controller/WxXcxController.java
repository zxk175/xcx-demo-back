package com.zxk175.xcx.module.controller;

import com.zxk175.xcx.common.Response;
import com.zxk175.xcx.module.bean.WxXcxDecodePhoneParam;
import com.zxk175.xcx.module.bean.WxXcxLoginParam;
import com.zxk175.xcx.module.service.IWxXcxAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zxk175
 * @since 2020-03-18 10:02
 */
@Controller
@AllArgsConstructor
@RequestMapping("/wx-xcx")
@Api(tags = "WxXcxAuth 微信小程序")
public class WxXcxController {

    private IWxXcxAuthService wxXcxAuthService;


    @ResponseBody
    @PostMapping("/auth-login/v1")
    @ApiOperation(value = "微信小程序授权登录", notes = "微信小程序授权登录")
    public Response<?> authLogin(@Validated @RequestBody WxXcxLoginParam param) {
        return wxXcxAuthService.authLogin(param);
    }

    @ResponseBody
    @PostMapping("/decode-phone/v1")
    @ApiOperation(value = "微信小程序解密手机号", notes = "微信小程序解密手机号")
    public Response<?> decodePhone(@Validated @RequestBody WxXcxDecodePhoneParam param) {
        return wxXcxAuthService.decodePhone(param);
    }
}