package com.zxk175.xcx.common.consts;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxk175
 * @since 2020-03-18 10:16
 */
public class Const {

    public static final String UTF_8 = "UTF-8";
    public static final Charset UTF_8_OBJ = StandardCharsets.UTF_8;

    public static final String JSON_ERR_CODE = "errcode";
    public static final String JSON_SESSION_KEY = "session_key";

    public static final String REDIS_WX_XCX_SESSION_KEY = "wx-xcx:session-key";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static SerializerFeature[] serializerFeatures(Boolean prettyFormat) {
        List<SerializerFeature> serializerFeatures = new ArrayList<>(16);
        // 输出key时是否使用双引号,默认为true
        serializerFeatures.add(SerializerFeature.QuoteFieldNames);
        // 避免循环引用
        serializerFeatures.add(SerializerFeature.DisableCircularReferenceDetect);
        // 是否输出Map值为null的字段
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
        // 数值字段如果为null,输出为0,而非null
        serializerFeatures.add(SerializerFeature.WriteNullNumberAsZero);
        // 字符类型字段如果为null,输出为"",而非null
        serializerFeatures.add(SerializerFeature.WriteNullStringAsEmpty);
        // list字段如果为null,输出为[],而非null
        serializerFeatures.add(SerializerFeature.WriteNullListAsEmpty);
        // boolean字段如果为null,输出为false,而非null
        serializerFeatures.add(SerializerFeature.WriteNullBooleanAsFalse);
        // 设置使用文本方式输出日期，fastJson默认是long
        serializerFeatures.add(SerializerFeature.WriteDateUseDateFormat);
        // 将key不是String类型转为String
        serializerFeatures.add(SerializerFeature.WriteNonStringKeyAsString);
        // 忽略那些抛异常的get方法
        serializerFeatures.add(SerializerFeature.IgnoreErrorGetter);
        // 启用对'<'和'>'的处理方式
        serializerFeatures.add(SerializerFeature.BrowserSecure);
        if (prettyFormat) {
            serializerFeatures.add(SerializerFeature.PrettyFormat);
        }

        return serializerFeatures.toArray(new SerializerFeature[0]);
    }

    public static SerializeConfig serializeConfig() {
        SerializeConfig config = new SerializeConfig();
        config.put(java.util.Date.class, new SimpleDateFormatSerializer(Const.DEFAULT_DATE_FORMAT));
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer(Const.DEFAULT_DATE_FORMAT));

        return config;
    }

}
