package com.mycompany.tokenizer.application.proof;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.mycompany.sdk.storage.RedisStorage;
import com.mycompany.sdk.storage.Storage;

@SpringBootApplication
public class ProofApplication {

	public static void main(String[] args)
	{
		String configFile = System.getProperty("config.file");
		if (configFile != null && (new File(configFile)).exists())
			System.setProperty("spring.config.location", "classpath:application.properties," + configFile);
		else
			throw new IllegalArgumentException(
					"Path to configuration file is invalid. Set correct value for the \"config.file\" property.");

		SpringApplication.run(ProofApplication.class, args);
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host}") String host, @Value("${redis.port}") int port) 
	{
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(host, port);
	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(conf);
	    return jedisConFactory;
	}
	
	@Bean
	public Storage<String, String> redisStorage(RedisTemplate<String, String> redisTemplate) 
	{
	    return new RedisStorage<>(redisTemplate);
	}
}

