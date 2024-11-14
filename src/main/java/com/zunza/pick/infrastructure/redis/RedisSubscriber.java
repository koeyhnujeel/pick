package com.zunza.pick.infrastructure.redis;

import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisSubscriber implements MessageListener {

	private final RedisTemplate<String, Object> redisTemplate;
	private final CacheManager cacheManager;

	public RedisSubscriber(RedisMessageListenerContainer redisMessageListener,
		RedisTemplate<String, Object> redisTemplate, CacheManager cacheManager) {
		redisMessageListener.addMessageListener(this, new ChannelTopic("productChannel"));
		this.redisTemplate = redisTemplate;
		this.cacheManager = cacheManager;
	}

	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		// String body = redisTemplate.getStringSerializer().deserialize(message.getBody());
		Objects.requireNonNull(cacheManager.getCache("productsCache")).clear();
		log.info("productsCache clear");
	}
}
