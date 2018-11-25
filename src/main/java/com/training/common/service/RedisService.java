package com.training.common.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
    JedisPool pool = null;

    public RedisService() {
        pool = new JedisPool(new JedisConfig(), "192.168.1.25", 6379, 1000, "123456");
    }

    public <T> void set(String key, T value) {
        String jsonString = JSONObject.toJSONString(value);
        set(key, jsonString);
    }

    public <T> T get(String key, Class clazz) {
        Jedis jedis = null;
        T result;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            result = (T) JSONObject.parseObject(value, clazz);
        } finally {
            jedis.close();
        }
        return result;
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
//            jedis.setex()
        } finally {
            jedis.close();
        }
    }

    private class JedisConfig extends GenericObjectPoolConfig {
        public JedisConfig() {
            this.setTestWhileIdle(true);
            this.setMinEvictableIdleTimeMillis(30000L);
            this.setTimeBetweenEvictionRunsMillis(30000L);
            this.setNumTestsPerEvictionRun(-1);
        }
    }

}
