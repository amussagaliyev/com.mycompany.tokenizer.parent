package com.mycompany.beans;

public class TransactionToken
{
	private String transactionId;
	private String token;
	
	public TransactionToken()
	{
	}
	public TransactionToken(String transactionId, String token)
	{
		this.transactionId = transactionId;
		this.token = token;
	}
	public String getTransactionId()
	{
		return transactionId;
	}
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	
	
}
