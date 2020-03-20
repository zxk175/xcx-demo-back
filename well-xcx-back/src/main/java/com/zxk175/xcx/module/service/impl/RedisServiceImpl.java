package com.zxk175.xcx.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zxk175.xcx.module.service.IRedisService;
import com.zxk175.xcx.util.MyStrUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zxk175
 * @since 2020-03-18 10:35
 */
@Service
@AllArgsConstructor
public class RedisServiceImpl implements IRedisService {

    private StringRedisTemplate stringRedisTemplate;
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void setStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setObj(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }

    @Override
    public void setObj(String key, Object object, Long ttl) {
        redisTemplate.opsForValue().set(key, object, ttl, TimeUnit.SECONDS);
    }

    @Override
    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getObj(String key, Class<T> clazz) {
        String str = getStr(key);
        if (MyStrUtil.isBlank(str)) {
            return null;
        }

        return JSONObject.parseObject(str, clazz);
    }

    @Override
    public boolean removeKey(String key) {
        if (existsKey(key)) {
            return Optional.ofNullable(redisTemplate.delete(key)).orElse(false);
        }

        return true;
    }

    @Override
    public boolean existsKey(String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false);
    }

    @Override
    public boolean notExistsKey(String key) {
        return !existsKey(key);
    }

}
