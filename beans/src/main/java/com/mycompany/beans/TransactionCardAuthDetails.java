package com.mycompany.beans;

public class TransactionCardAuthDetails
{
	private String transactionId;
	private CardAuthDetails cardAuthDetails;
	
	
	public TransactionCardAuthDetails()
	{
	}

	public TransactionCardAuthDetails(String transactionId, CardAuthDetails cardAuthDetails)
	{
		this.transactionId = transactionId;
		this.cardAuthDetails = cardAuthDetails;
	}
	
	public String getTransactionId()
	{
		return transactionId;
	}
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	public CardAuthDetails getCardAuthDetails()
	{
		return cardAuthDetails;
	}
	public void setCardAuthDetails(CardAuthDetails cardAuthDetails)
	{
		this.cardAuthDetails = cardAuthDetails;
	}
	
	@Override
	public String toString()
	{
		return "{\"" + transactionId + "\":" + cardAuthDetails.toString() + "}";
	}
}
