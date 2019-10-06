package com.application.seckilling.shopping.redis;

public interface GoodRedisService {

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return
     */
    void addCache(byte[] key, byte[] value);

    /**
     * 查找缓存
     *
     * @param key
     * @return
     */
    byte[] queryCache(byte[] key);

    /**
     * 是否存在该key的缓存
     *
     * @param key
     * @return
     */
    boolean existKey(byte[] key);
}
