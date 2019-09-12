package com.application.redis;

public interface GoodRedisService {

    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    public void addCache(String key,String value);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean deleteCache(String key);

    /**
     * 更新缓存
     * @param key
     * @param value
     * @return
     */
    public void updateCache(String key,String value);

    /**
     * 查找缓存
     * @param key
     * @return
     */
    public String queryCache(String key);

    /**
     * 是佛存在该key的缓存
     * @param key
     * @return
     */
    public boolean existKey(String key);
}
