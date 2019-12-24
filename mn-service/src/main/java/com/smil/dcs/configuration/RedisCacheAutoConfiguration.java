/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: RedisCacheAutoConfiguration.java
 * Author:   chenliang
 * Date:     2018年12月3日 下午6:28:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.configuration;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smil.dcs.common.GlobalConsts;
import com.smil.dcs.enums.CacheEnums;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * redis缓存配置.<br> 
 *
 * @author chenliang
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {
    
    /**
     * 功能描述: <br>
     * 非String对象缓存，使用Jackson2Json进行序列化
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        // Jackson2Json序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    /**
     * 功能描述: <br>
     * key自动生成策略.<br>
     * 如: <code>@Cacheable(value = "DefaultKeyTest", keyGenerator = "simpleKeyGenerator")</code>
     *
     * @return
     */
    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            
            // 参数Json后相加再md5
            StringBuilder sb = new StringBuilder();
            for (Object obj : objects) {
                sb.append(JSON.toJSONString(obj));
            }
            stringBuilder.append(DigestUtils.md5Hex(sb.toString()));
            
            stringBuilder.append("]");
            return stringBuilder.toString();
        };
    }

    /**
     * 功能描述: <br>
     * 缓存管理器.
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
            RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
            // 默认策略无限，未配置的 key 会使用这个
            this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.SEC00), 
            this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }
    
    /**
     * 功能描述: <br>
     * 按缓存时间设置缓存名.
     *
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.SEC00.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.SEC00));
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.MIN05.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.MIN05));
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.MIN10.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.MIN10));
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.MIN30.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.MIN30));
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.MIN60.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.MIN60));
        redisCacheConfigurationMap.put(CacheEnums.CacheExpireEnum.HOUR24.getValue(), 
                this.getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum.HOUR24));

        return redisCacheConfigurationMap;
    }

    /**
     * 功能描述: <br>
     * 设置默认用Jackson2Json序列化
     *
     * @param seconds
     * @return
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(CacheEnums.CacheExpireEnum seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                // 不增加前缀, 由调用方自己设置 .prefixKeysWith(CacheNamespace.DCS+":"+seconds.getValue()+":")
                // 前缀设置空串，以此替换掉@Cacheable的value
                .prefixKeysWith(StringUtils.EMPTY)
                .entryTtl(Duration.ofSeconds(seconds.getCode()));
    }
    
    /**
     * 功能描述: <br>
     * 获取分布锁, 默认60s.
     *
     * @param key The key prefix for locks.
     * @return the redis lock registry
     */
    @Bean
    public RedisLockRegistry getRedisLockRegistry(LettuceConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, GlobalConsts.DIST_LOCK, 60L * 1000L);
    }
}
