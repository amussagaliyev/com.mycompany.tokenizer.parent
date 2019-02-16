package com.mycompany.sdk.storage;

import org.springframework.stereotype.Component;

@Component
public class RedisStorage<K, T> implements Storage<K, T>
{

	@Override
	public void put(K key, T obj)
	{
	}

	@Override
	public T get(K key)
	{
		return null;
	}

}
