package com.zunza.pick.infrastructure.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisher {

	private final RedisTemplate<String, Object> redisTemplate;

	public RedisPublisher(final RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void publish(ChannelTopic topic) {
		redisTemplate.convertAndSend(topic.getTopic(), "");
	}
}
