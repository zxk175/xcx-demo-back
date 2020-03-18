package com.zxk175.xcx.module.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.sd4324530.jtuple.Tuple2;
import com.zxk175.xcx.bean.WxXcxLoginParam;
import com.zxk175.xcx.common.Response;
import com.zxk175.xcx.common.consts.Const;
import com.zxk175.xcx.module.service.IRedisService;
import com.zxk175.xcx.module.service.IWxXcxAuthService;
import com.zxk175.xcx.util.AesUtil;
import com.zxk175.xcx.util.WxXcxAccessUtil;
import com.zxk175.xcx.util.json.FastJsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zxk175
 * @since 2020-03-18 09:58
 */
@Slf4j
@Service
@AllArgsConstructor
public class WxXcxAuthServiceImpl implements IWxXcxAuthService {

    private IRedisService redisService;


    @Override
    public Response<?> authLogin(WxXcxLoginParam param) {
        Tuple2<JSONObject, Response<?>> tuple = WxXcxAccessUtil.getSession(redisService, param.getCode());
        JSONObject result = tuple.first;
        if (ObjectUtil.isNull(result)) {
            return tuple.second;
        }

        // 获取会话密钥
        String sessionKey = result.getString(Const.JSON_SESSION_KEY);

        // 解密encryptedData加密数据
        String decryptResult = AesUtil.decrypt(param.getEncryptedData(), sessionKey, param.getIv(), Const.UTF_8);

        return Response.success(FastJsonUtil.toObject(decryptResult, JSONObject.class));
    }

}
