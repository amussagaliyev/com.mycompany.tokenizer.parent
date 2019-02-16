package com.mycompany.tokenizer.application.source.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.sdk.queue.Queue;

@Service
public class CardAuthSubmitter
{
	
	private Queue<TransactionCardAuthDetails> inputQueue;
	
	@Autowired
	public CardAuthSubmitter(Queue<TransactionCardAuthDetails> inputQueue)
	{
		this.inputQueue = inputQueue;
	}

	public String submit(CardAuthDetails cardAuthDetails)
	{
		String transactionId = UUID.randomUUID().toString();
		inputQueue.submit(new TransactionCardAuthDetails(transactionId, cardAuthDetails));
		return transactionId;
	}
}
