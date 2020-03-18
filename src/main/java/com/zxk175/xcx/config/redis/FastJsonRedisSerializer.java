package com.zxk175.xcx.config.redis;

import com.zxk175.xcx.common.consts.Const;
import com.zxk175.xcx.util.json.FastJsonUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author zxk175
 * @since 17/6/17
 */
public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    FastJsonRedisSerializer() {
        super();
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }

        return FastJsonUtil.jsonStrByMy(t).getBytes(Const.UTF_8_OBJ);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String str = new String(bytes, Const.UTF_8_OBJ);
        return FastJsonUtil.toObject(str, Object.class);
    }

}
