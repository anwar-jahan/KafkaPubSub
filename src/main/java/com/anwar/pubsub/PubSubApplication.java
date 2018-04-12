package com.anwar.pubsub;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class PubSubApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PubSubApplication.class);
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(PubSubApplication.class, args);
		
		KafkaTemplate<String, String> template = ctx.getBean(KafkaTemplate.class);
		
		CountDownLatch latch = ctx.getBean(CountDownLatch.class);
		
		LOGGER.info("Sending message...");
		template.sendDefault("chat", "Hello from Redis!");
		latch.await();

		System.exit(0);
	}
	

	


}
