package com.zxk175.xcx.module.controller;

import com.zxk175.xcx.bean.WxXcxLoginParam;
import com.zxk175.xcx.common.Response;
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
@RequestMapping("/wx-xcx/auth")
@Api(tags = "WxXcxAuth 微信小程序授权")
public class WxXcxAuthController {

    private IWxXcxAuthService wxXcxAuthService;


    @ResponseBody
    @PostMapping("/login/v1")
    @ApiOperation(value = "微信小程序授权登录", notes = "微信小程序授权登录")
    public Response<?> authLogin(@Validated @RequestBody WxXcxLoginParam param) {
        return wxXcxAuthService.authLogin(param);
    }

}