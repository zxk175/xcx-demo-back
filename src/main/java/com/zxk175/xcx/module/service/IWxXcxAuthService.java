package com.zxk175.xcx.module.service;

import com.zxk175.xcx.bean.WxXcxLoginParam;
import com.zxk175.xcx.common.Response;

/**
 * @author zxk175
 * @since 2020-03-18 09:58
 */
public interface IWxXcxAuthService {

    /**
     * 微信授权登录
     *
     * @param param ignore
     * @return ignore
     */
    Response<?> authLogin(WxXcxLoginParam param);

}
