package com.mycompany.sdk.queue;

public interface Queue<T>
{
	public void submit(T obj);
	public T dequeue();
}
