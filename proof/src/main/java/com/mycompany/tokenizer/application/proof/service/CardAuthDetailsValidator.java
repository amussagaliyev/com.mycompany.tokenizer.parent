package com.mycompany.tokenizer.application.proof.service;

import java.util.Base64;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.cipher.JceCipher;
import com.mycompany.sdk.storage.Storage;

@EnableKafka
@Service
public class CardAuthDetailsValidator
{
	private Storage<String, String> redisStorage;
	
	public CardAuthDetailsValidator(Storage<String, String> redisStorage)
	{
		this.redisStorage = redisStorage;
	}

	@KafkaListener(topics = "${com.mycompany.kafka.outputTopic}")
	public void validate(TransactionToken transactionToken)
	{
		TransactionCardAuthDetails transactionCardAuthDetails = buildTransactionCardAuthDetailsFromToken(transactionToken);
		System.out.println(transactionCardAuthDetails);
	}
	
	private TransactionCardAuthDetails buildTransactionCardAuthDetailsFromToken(TransactionToken transactionToken)
	{
		String decryptedToken = decryptToken(transactionToken);
		String[] cardAuthAttributes = decryptedToken.split(",");
		
		CardAuthDetails cardAuthDetails = new CardAuthDetails();
		cardAuthDetails.setCardNumber(cardAuthAttributes[0]);
		cardAuthDetails.setExpirationDate(cardAuthAttributes[1]);
		cardAuthDetails.setCvv(cardAuthAttributes[2]);
		
		return new TransactionCardAuthDetails(transactionToken.getTransactionId(), cardAuthDetails);
	}
	
	private String decryptToken(TransactionToken transactionTokenMap)
	{
		byte[] keyBase64Bytes = redisStorage.get(transactionTokenMap.getTransactionId()).getBytes();
		byte[] key = Base64.getDecoder().decode(keyBase64Bytes);
		CipherProvider cipherProvider = new JceCipher(key);
		String decryptedToken = cipherProvider.decryptAsBase64ToString(transactionTokenMap.getToken());
		return decryptedToken;
	}
}
