package com.zxk175.xcx.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/ss498976239/article/details/83687438
 *
 * @author zxk175
 * @since 2019-09-19 09:47
 */
@Slf4j
public class OkHttpUtil {

    /**
     * 会被多线程使用，所以使用关键字volatile
     */
    private volatile static OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;


    /**
     * 私有化构造方法
     */
    private OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例模式，全局1个OkHttpUtil对象
     *
     * @return OkHttpUtil
     */
    public static OkHttpUtil instance() {
        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }

        return okHttpUtil;
    }

    public String getStr(String url, Map<String, String> param) {
        return getCommon(url, param);
    }

    public JSONObject getJsonObject(String url, Map<String, String> param) {
        String responseString = getCommon(url, param);

        return JSONObject.parseObject(responseString, JSONObject.class);
    }

    private String getCommon(String url, Map<String, String> param) {
        // 请求参数
        HttpUrl.Builder builder = HttpUrl.get(url).newBuilder();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        // 请求创建
        Request request = new Request.Builder()
                .url(builder.build().toString())
                .build();

        return executeRequest(okHttpClient, request);
    }

    /**
     * 提交json数据
     *
     * @param url   ignore
     * @param param ignore
     * @return ignore
     */
    public String postJson(String url, String param) {
        Request request = postJsonCommon(url, param);

        return executeRequest(okHttpClient, request);
    }

    public JSONObject postJsonObject(String url, Map<?, ?> param) {
        Request request = postJsonCommon(url, JSONObject.toJSONString(param));

        String responseString = executeRequest(okHttpClient, request);

        return JSONObject.parseObject(responseString, JSONObject.class);
    }

    private Request postJsonCommon(String url, String param) {
        // 请求参数
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_VALUE);
        RequestBody body = RequestBody.create(mediaType, param);

        // 请求创建
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    private static String executeRequest(OkHttpClient okHttpClient, Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("requestUri：{}", request.url());
            log.info("responseMessage：{}", response.message());

            ResponseBody responseBody = response.body();
            if (ObjectUtil.isNull(responseBody)) {
                return MyStrUtil.EMPTY;
            }

            return responseBody.string();
        } catch (Exception ex) {
            throw new RuntimeException("发送请求异常", ex);
        } finally {
            if (ObjectUtil.isNotNull(response)) {
                response.close();
            }
        }
    }

    private static OkHttpClient buildBasicAuthClient(final String user, final String password) {
        return new OkHttpClient.Builder().authenticator((route, response) -> {
            String credential = Credentials.basic(user, password);
            return response.request().newBuilder().header("Authorization", credential).build();
        }).build();
    }

    public static String postBasicAuth(String url, String param, final String user, final String password) {
        OkHttpClient okHttpClient = buildBasicAuthClient(user, password);

        // 请求参数
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_VALUE);
        RequestBody body = RequestBody.create(mediaType, param);

        // 请求创建
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return executeRequest(okHttpClient, request);
    }

}