package com.mycompany.tokenizer.application.flow;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.cipher.JceCipher;
import com.mycompany.sdk.queue.KafkaQueue;
import com.mycompany.sdk.queue.Queue;
import com.mycompany.sdk.storage.RedisStorage;
import com.mycompany.sdk.storage.Storage;

@SpringBootApplication
public class FlowApplication
{

	public static void main(String[] args)
	{
		String configFile = System.getProperty("config.file");
		if (configFile != null && (new File(configFile)).exists())
			System.setProperty("spring.config.location", System.getProperty("config.file"));
		else
			throw new IllegalArgumentException("Path to configuration file is invalid. Set correct value for the \"config.file\" property.");

		SpringApplication.run(FlowApplication.class, args);
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host}") String host, @Value("${redis.port}") int port) 
	{
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(host, port);
	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(conf);
	    return jedisConFactory;
	}
	
	@Bean
	public Queue<TransactionCardAuthDetails> inputQueue() 
	{
	    return new KafkaQueue<>();
	}

	@Bean
	public Queue<TransactionToken> outputQueue() 
	{
	    return new KafkaQueue<>();
	}

	@Bean
	public Storage<String, String> redisStorage() 
	{
	    return new RedisStorage<>();
	}

	@Bean
	public CipherProvider jceCipher() 
	{
	    return new JceCipher();
	}

}
