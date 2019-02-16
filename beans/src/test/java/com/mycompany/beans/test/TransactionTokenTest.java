package com.mycompany.beans.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mycompany.beans.TransactionToken; 

public class TransactionTokenTest
{
	
	@Test
	public void transactionId_getter_setter()
	{
		TransactionToken transactionToken = new TransactionToken();
		String transactionId = UUID.randomUUID().toString();
		transactionToken.setTransactionId(transactionId);
		assertTrue(transactionId.equals(transactionToken.getTransactionId()));
	}

	@Test
	public void cardAuthDetails_getter_setter()
	{
		TransactionToken transactionToken = new TransactionToken();
		String token = "test token";
		transactionToken.setToken(token);
		assertTrue(token.equals(transactionToken.getToken()));
	}
}
