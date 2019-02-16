package com.mycompany.sdk.queue;

import org.springframework.stereotype.Component;

@Component
public class KafkaQueue<T> implements Queue<T>
{

	@Override
	public void submit(T obj)
	{
	}

	@Override
	public T dequeue()
	{
		return null;
	}

}
