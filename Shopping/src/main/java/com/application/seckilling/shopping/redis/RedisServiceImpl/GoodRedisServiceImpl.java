package com.application.seckilling.shopping.redis.RedisServiceImpl;


import com.application.seckilling.shopping.redis.GoodRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GoodRedisServiceImpl implements GoodRedisService {

    @Autowired
    private RedisTemplate<byte[], Object> redisTemplate;

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public void addCache(byte[] key, byte[] value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 查询key对应的value
     * @param key
     * @return
     */
    @Override
    public byte[] queryCache(byte[] key) {
        return (byte[]) redisTemplate.opsForValue().get(key);
    }

    /**
     * 是否存在该key
     *
     * @param key
     * @return
     */
    @Override
    public boolean existKey(byte[] key) {
        return redisTemplate.hasKey(key);
    }
}
