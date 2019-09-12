package com.application.redis.RedisServiceImpl;


import com.application.redis.GoodRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GoodRedisServiceImpl implements GoodRedisService {

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public void addCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    @Override
    public boolean deleteCache(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 更新缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public void updateCache(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String queryCache(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 是否存在该key
     * @param key
     * @return
     */
    @Override
    public boolean existKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
