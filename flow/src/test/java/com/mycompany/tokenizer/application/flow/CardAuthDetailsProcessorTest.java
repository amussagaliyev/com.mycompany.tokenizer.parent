package com.mycompany.tokenizer.application.flow;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.storage.Storage;
import com.mycompany.tokenizer.application.flow.processor.CardAuthDetailsProcessor;

public class CardAuthDetailsProcessorTest
{
	@Mock
	private KafkaTemplate<String, TransactionToken> outputQueue;
	
	@Mock
	private CipherProvider cipherProvider;
	
	@Mock
	private Storage<String, String> redisStorage;

	
	@BeforeEach
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testProcess()
	{
		TransactionCardAuthDetails transactionCardAuthDetails = buildTransactionCardAuthDetails();

		Mockito.when(cipherProvider.getKeyAsBase64()).thenReturn("JYlMs/QH4DvlDauP6b/3GsfmRf6o6pTg");

		CardAuthDetailsProcessor cardAuthDetailsProcessor = new CardAuthDetailsProcessor(outputQueue, cipherProvider, redisStorage);
		cardAuthDetailsProcessor.process(transactionCardAuthDetails);
	}
	
	private TransactionCardAuthDetails buildTransactionCardAuthDetails()
	{
		String transactionId = UUID.randomUUID().toString();
		CardAuthDetails cardAuthDetails = new CardAuthDetails();
		return new TransactionCardAuthDetails(transactionId, cardAuthDetails);
	}
}
