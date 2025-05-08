package com.zhangjun.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author zhangjun
 * @Date 2025/4/24 16:09
 * @Version 1.0
 */
public interface RedisService {

    /**
     * 保存属性
     */
    void set(String key, Object value,long timeout);

    /**
     * 保存属性
     */
    void set(String key, Object value);

    /**
     * 获取属性
     */
    Object get(String key);

    /**
     * 删除属性
     */
    Boolean delete(String key);


    /**
     * 批量删除属性
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @return
     */
    Boolean expire(String key, long timeout);

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 按delta递增
     * @param key
     * @param delta
     * @return
     */
    Long incr(String key,long delta);

    /**
     * 按delta递减
     * @param key
     * @param delta
     * @return
     */
    Long decr(String key,long delta);

    /**
     * 获取Hash结构中的属性
     * @param key
     * @param hashKey
     * @return
     */
    Object hGet(String key,Object hashKey);

    /**
     * 向Hash结构中放入一个属性
     * @param key
     * @param hashKey
     * @param value
     * @param timeout
     * @return
     */
    Boolean hSet(String key,Object hashKey,Object value,long timeout);

    /**
     * 向Hash结构中放入一个属性
     * @param key
     * @param hashKey
     * @param value
     */
    void hSet(String key,Object hashKey,Object value);

    public Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    void hSetAll(String key, Map<String, Object> map);

    /**
     * 直接设置带有有效期的整个Hash结构
     * @param key
     * @param map
     * @param time
     * @return
     */
    public Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * 删除Hash结构中的属性
     */
    void hDel(String key, Object... hashKey);

    /**
     * 判断Hash结构中是否有该属性
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Hash结构中属性递增
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * Hash结构中属性递减
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * 获取Set结构
     */
    Set<Object> sMembers(String key);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, Object... values);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * 是否为Set中的属性
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取Set结构的长度
     */
    Long sSize(String key);

    /**
     * 删除Set结构中的属性
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取List结构中的属性
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    Long lSize(String key);

    /**
     * 根据索引获取List中的属性
     */
    Object lIndex(String key, long index);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, Object value);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, Object value, long time);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, Object... values);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * 从List结构中移除属性
     */
    Long lRemove(String key, long count, Object value);

}
