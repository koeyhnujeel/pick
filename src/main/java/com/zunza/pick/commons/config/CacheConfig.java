package com.zunza.pick.commons.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.zunza.pick.commons.cache.CacheType;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {
		List<CaffeineCache> caches = Arrays.stream(CacheType.values())
			.map(cache -> new CaffeineCache(
				cache.getCacheName(),
				Caffeine.newBuilder()
					.expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.MINUTES)
					.maximumSize(cache.getMaximumSize())
					.build()
			))
			.toList();

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
		return redisTemplate;
	}

	@Bean
	public RedisMessageListenerContainer RedisMessageListener(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		return container;
	}

	@Bean
	public ChannelTopic channelTopic() {
		return new ChannelTopic("productChannel");
	}
}
