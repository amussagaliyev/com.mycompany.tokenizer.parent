package com.mycompany.sdk.storage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisStorage<K, T> implements Storage<K, T>
{
    private static final String KEY = "Token";

    private RedisTemplate<K, T> redisTemplate;

    private HashOperations<String, K, T> hashOperations;

    @Autowired
    public RedisStorage(RedisTemplate<K, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = (HashOperations<String, K, T>) redisTemplate.opsForHash();
    }


	@Override
	public void put(K key, T obj) {
        hashOperations.put(KEY, key, obj);
	}

	@Override
	public T get(K key)
	{
		return hashOperations.get(KEY, key);
	}

}
