package com.mycompany.tokenizer.application.flow.processor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.queue.Queue;
import com.mycompany.sdk.storage.Storage;

@Service
public class CardAuthDetailsProcessor
{
	private Queue<TransactionCardAuthDetails> inputQueue;
	private Queue<TransactionToken> outputQueue;
	private CipherProvider cipherProvider;
	private Storage<String, String> redisStorage;
	
	public CardAuthDetailsProcessor(Queue<TransactionCardAuthDetails> inputQueue, Queue<TransactionToken> outputQueue,
			CipherProvider cipherProvider, Storage<String, String> redisStorage)
	{
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
		this.cipherProvider = cipherProvider;
		this.redisStorage = redisStorage;
	}

	@Transactional
	public void process()
	{
		TransactionCardAuthDetails transactionCardAuthDetails = inputQueue.dequeue();
		String token = buildToken(transactionCardAuthDetails);
		outputQueue.submit(new TransactionToken(transactionCardAuthDetails.getTransactionId(), token));
		redisStorage.put(transactionCardAuthDetails.getTransactionId(), cipherProvider.getKeyAsBase64());
	}
	
	private String buildToken(TransactionCardAuthDetails transactionCardAuthDetails)
	{
		CardAuthDetails cardAuthDetails = transactionCardAuthDetails.getCardAuthDetails();
		String raw = cardAuthDetails.getCardNumber() + "," + cardAuthDetails.getExpirationDate() + "," + cardAuthDetails.getCvv();
		String token = cipherProvider.encryptToBase64String(raw);
		return token;
	}
}
