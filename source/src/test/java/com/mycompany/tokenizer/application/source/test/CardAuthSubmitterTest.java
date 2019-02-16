package com.mycompany.tokenizer.application.source.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.sdk.queue.Queue;
import com.mycompany.tokenizer.application.source.service.CardAuthSubmitter;

public class CardAuthSubmitterTest
{

	@BeforeEach
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private Queue<TransactionCardAuthDetails> inputQueue;

	@Mock
	private TransactionCardAuthDetails transactionCardAuthDetails;
	
	@Mock
	private CardAuthDetails cardAuthDetails;
	
	@Test
	public void whenSubmit_Then_ResultNotNull()
	{
		CardAuthSubmitter cardAuthSubmitter = new CardAuthSubmitter(inputQueue);
		String transactionId = cardAuthSubmitter.submit(cardAuthDetails);
		assertNotNull(transactionId);
		assertTrue(transactionId.length() > 0);
	}
}
