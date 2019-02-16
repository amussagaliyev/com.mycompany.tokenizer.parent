package com.mycompany.tokenizer.application.source.controller;

public class CardAuthResponse
{
	private boolean success;
	private String transactionId;
	
	public CardAuthResponse(boolean success, String transactionId)
	{
		this.success = success;
		this.transactionId = transactionId;
	}

	public boolean isSuccess()
	{
		return success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	public String getTransactionId()
	{
		return transactionId;
	}
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	
	@Override
	public String toString()
	{
		return "{\"success\":\"" + success + "\",\"transactionId\":\"" + transactionId + "\"}";
	}
}
