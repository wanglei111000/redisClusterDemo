package com.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("nn", "0000111");
        String name = redisTemplate.opsForValue().get("nn");
        System.out.println(name); //输出admin
    }
}
