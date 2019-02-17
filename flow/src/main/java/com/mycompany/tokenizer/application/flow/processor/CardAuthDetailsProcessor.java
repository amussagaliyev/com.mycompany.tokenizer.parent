package com.mycompany.tokenizer.application.flow.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.storage.Storage;

@EnableKafka
@Service
public class CardAuthDetailsProcessor
{
	private CipherProvider cipherProvider;
	private Storage<String, String> redisStorage;
	private KafkaTemplate<String, TransactionToken> outputKafkaTemplate;
	
	@Value("${com.mycompany.kafka.outputTopic}")
	private String outputKafkaTopic;
	
	public CardAuthDetailsProcessor(KafkaTemplate<String, TransactionToken> outputKafkaTemplate, CipherProvider cipherProvider, 
			Storage<String, String> redisStorage)
	{
		this.outputKafkaTemplate = outputKafkaTemplate;
		this.cipherProvider = cipherProvider;
		this.redisStorage = redisStorage;
	}

	@Transactional
	@KafkaListener(topics = "${com.mycompany.kafka.inputTopic}")
	public void process(TransactionCardAuthDetails transactionCardAuthDetails)
	{
		String token = buildToken(transactionCardAuthDetails);
		outputKafkaTemplate.send(outputKafkaTopic, new TransactionToken(transactionCardAuthDetails.getTransactionId(), token));
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
