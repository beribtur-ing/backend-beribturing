package ing.beribtur.proxy.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    //
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Save data with expiration time
    public void save(String key, String value, long duration) {
        //
        redisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
    }

    // Retrieve data
    public String get(String key) {
        //
        return redisTemplate.opsForValue().get(key);
    }

    // Delete data
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
