package com.zxk175.xcx.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zxk175.xcx.common.consts.Const;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;


/**
 * @author jiangxl
 * @since 2019/03/23 20:42
 */
@Slf4j
public class FastJsonUtil {

    private static final SerializeConfig CONFIG = Const.serializeConfig();
    private static final SerializerFeature[] FEATURES = Const.serializerFeatures(false);
    private static final FastJsonValueFilter FAST_JSON_VALUE_FILTER = new FastJsonValueFilter();

    static {
        // 修改全局的全局日期格式
        JSON.DEFFAULT_DATE_FORMAT = Const.DEFAULT_DATE_FORMAT;
    }

    public static String jsonStr(Object object) {
        return jsonStrByMy(object);
    }

    public static String jsonStrByMy(Object object) {
        return JSON.toJSONString(object, CONFIG, FAST_JSON_VALUE_FILTER, FEATURES);
    }

    public static String jsonStrByMy(Object object, SerializeFilter[] filters) {
        return JSON.toJSONString(object, CONFIG, filters, FEATURES);
    }

    public static String jsonStrByNotEmpty(Object object) {
        return JSON.toJSONString(object, CONFIG);
    }

    public static String jsonPrettyFormat(Object json) {
        return JSON.toJSONString(json, Const.serializerFeatures(true));
    }

    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> T toObject(String jsonStr, Type clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

}
