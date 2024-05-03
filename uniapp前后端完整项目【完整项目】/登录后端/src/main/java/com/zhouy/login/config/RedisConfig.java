package com.zhouy.login.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

@Configuration
public class RedisConfig {
    //这里是针对redisTemplate操作的序列化和反序列化
    @Bean
    public RedisTemplate<String,Object> redisTemplateInit(RedisConnectionFactory connectionFactory) {
        //创建redisTemplate对象
        RedisTemplate<String,Object> template =new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //创建json序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer =new GenericJackson2JsonRedisSerializer();

        //设置序列化Key的工具
        template.setKeySerializer(RedisSerializer.string());
        //设置序列化Value的工具
        template.setValueSerializer(jsonRedisSerializer);
        // 设置 hash 的 key
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置 hash 的 value
        template.setHashValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    //这里是针对springCache的序列化和反序列化
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<String,Object> redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        //设置Redis缓存有效期为1天
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).entryTtl(Duration.ofMinutes(5));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }





}
