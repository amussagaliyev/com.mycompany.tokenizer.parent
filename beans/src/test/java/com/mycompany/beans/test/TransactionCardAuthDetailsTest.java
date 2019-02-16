package com.mycompany.beans.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;

public class TransactionCardAuthDetailsTest
{
	private TransactionCardAuthDetails tcad;
	
	@BeforeEach
	public void init()
	{
		tcad = new TransactionCardAuthDetails();
	}
	
	@Test
	public void transactionId_getter_setter()
	{
		String transactionId = UUID.randomUUID().toString();
		tcad.setTransactionId(transactionId);
		assertTrue(transactionId.equals(tcad.getTransactionId()));
	}
	
	@Test
	public void cardAuthDetails_getter_setter()
	{
		CardAuthDetails cardAuthDetails = new CardAuthDetails();
		tcad.setCardAuthDetails(cardAuthDetails);
		assertTrue(cardAuthDetails.equals(tcad.getCardAuthDetails()));
	}
}
