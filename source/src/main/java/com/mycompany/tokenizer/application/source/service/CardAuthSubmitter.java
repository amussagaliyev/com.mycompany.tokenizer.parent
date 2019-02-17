package com.mycompany.tokenizer.application.source.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;

@Service
public class CardAuthSubmitter
{
	
    private KafkaTemplate<String, TransactionCardAuthDetails> kafkaTemplate;
    
    @Value("${com.mycompany.kafka.inputTopic}")
	private String inputTopic;
	
	@Autowired
	public CardAuthSubmitter(KafkaTemplate<String, TransactionCardAuthDetails> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public String submit(CardAuthDetails cardAuthDetails)
	{
		String transactionId = UUID.randomUUID().toString();
		kafkaTemplate.send(inputTopic, new TransactionCardAuthDetails(transactionId, cardAuthDetails));
		return transactionId;
	}
}
