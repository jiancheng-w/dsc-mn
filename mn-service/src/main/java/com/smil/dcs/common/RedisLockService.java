/*
 * Copyright (C), 2013-2019, 上海赛可电子商务有限公司
 * FileName: RedisLockService.java
 * Author:   chenliang
 * Date:     2019年1月12日 下午6:17:37
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

/**
 * redis分布锁服务.<br> 
 * 一般情况请直接注入服务RedisLockRegistry,对过期时间有特殊要求再调用本服务中方法.</br>
 * 本服务方法中的RedisLockRegistry非单例，并发小使用.
 *
 * @author chenliang
 */
@Component
public class RedisLockService {
    
    /** The Constant DIS_LOCK. */
    public static final String DIST_LOCK = GlobalConsts.DIST_LOCK;
    
    /** The lettuce connection factory. */
    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;
    
    
    /**
     * 功能描述: <br>
     * 获取分布锁, 默认60s.
     *
     * @param key The key prefix for locks.
     * @return the redis lock registry
     */
    public RedisLockRegistry getRedisLockRegistry(String key) {
        return new RedisLockRegistry(lettuceConnectionFactory, DIST_LOCK+key, 60L * 1000L);
    }
    
    /**
     * 功能描述: <br>
     * 获取分布锁.
     *
     * @param key The key prefix for locks.
     * @param expire The expiration in seconds.
     * @return the redis lock registry
     */
    public RedisLockRegistry getRedisLockRegistry(String key, int expire) {
        return new RedisLockRegistry(lettuceConnectionFactory, DIST_LOCK+key, expire * 1000L);
    }
}
