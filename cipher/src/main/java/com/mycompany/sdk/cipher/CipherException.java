package com.mycompany.sdk.cipher;

public class CipherException extends RuntimeException
{
	public CipherException(String msg, Throwable e)
	{
		super(msg, e);
	}

	public CipherException(String msg)
	{
		super(msg);
	}
}
