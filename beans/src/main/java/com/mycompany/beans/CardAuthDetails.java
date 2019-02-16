package com.mycompany.beans;

public class CardAuthDetails
{
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public CardAuthDetails()
	{
    	
	}
    
    public CardAuthDetails(String cardNumber, String expirationDate, String cvv)
	{
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	public String getCardNumber()
    {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
    public String getExpirationDate()
    {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
    public String getCvv()
    {
        return cvv;
    }
    public void setCvv(String cvv)
    {
        this.cvv = cvv;
    }
    
    @Override
    public String toString()
    {
    	return "{\"cardNumber\":\"" + cardNumber + "\",\"expirationDate\":\"" + expirationDate + "\",\"cvv\":\"" + cvv + "\"}";
    }
}
