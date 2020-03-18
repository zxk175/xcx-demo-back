package com.zxk175.xcx.util;

import com.alibaba.fastjson.JSONObject;
import com.github.sd4324530.jtuple.Tuple2;
import com.github.sd4324530.jtuple.Tuples;
import com.zxk175.xcx.common.Response;
import com.zxk175.xcx.common.consts.Const;
import com.zxk175.xcx.common.consts.TxConst;
import com.zxk175.xcx.module.service.IRedisService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxk175
 * @since 2019/06/12 19:02
 */
public class WxXcxAccessUtil {

    public static Tuple2<JSONObject, Response<?>> getSession(IRedisService redisService, String code) {
        // 登录凭证不能为空
        if (MyStrUtil.isBlank(code)) {
            String sessionKey = redisService.getStr(Const.REDIS_WX_XCX_SESSION_KEY);
            JSONObject result = new JSONObject();
            result.put(Const.JSON_SESSION_KEY, sessionKey);

            return Tuples.tuple(result, Response.success());
        }

        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid+
        Map<String, String> params = new HashMap<>(8);
        params.put("js_code", code);
        params.put("appid", TxConst.XCX_APP_ID);
        params.put("secret", TxConst.XCX_APP_SECRET);
        params.put("grant_type", "authorization_code");

        JSONObject result = OkHttpUtil.instance().getJsonObject(TxConst.CODE2SESSION_URL, params);
        if (result == null) {
            return Tuples.tuple(null, Response.fail("code2session请求返回结果为空"));
        }

        Integer errCode = result.getInteger(Const.JSON_ERR_CODE);
        if (errCode == null) {
            String sessionKey = result.getString(Const.JSON_SESSION_KEY);
            redisService.setStr(Const.REDIS_WX_XCX_SESSION_KEY, sessionKey);

            return Tuples.tuple(result, Response.success(result));
        }

        return Tuples.tuple(null, Response.fail(errCode + " ：" + result.getString("errmsg")));
    }

}
