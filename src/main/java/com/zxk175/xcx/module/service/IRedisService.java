package com.zxk175.xcx.module.service;

/**
 * @author zxk175
 * @since 2020-03-18 10:35
 */
public interface IRedisService {

    void setStr(String key, String value);

    String getStr(String key);

    void setObj(String key, Object object);

    void setObj(String key, Object object, Long ttl);

    Object getObj(String key);

    <T> T getObj(String key, Class<T> clazz);

    boolean removeKey(String key);

    boolean existsKey(String key);

    boolean notExistsKey(String key);

}
