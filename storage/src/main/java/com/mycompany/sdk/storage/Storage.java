package com.mycompany.sdk.storage;

public interface Storage<K, T>
{
    public void put(K key, T obj);
    public T get(K key);
}
