package com.mycompany.tokenizer.application.proof.test;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.beans.TransactionCardAuthDetails;
import com.mycompany.beans.TransactionToken;
import com.mycompany.sdk.cipher.CipherProvider;
import com.mycompany.sdk.cipher.JceCipher;
import com.mycompany.sdk.storage.Storage;
import com.mycompany.tokenizer.application.proof.service.CardAuthDetailsValidator;

public class CardAuthDetailsValidatorTest
{
	@Mock
	private Storage<String, String> redisStorage;

	@BeforeEach
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidate()
	{
		CipherProvider cipherProvider = new JceCipher();
		CardAuthDetails cardAuthDetails = new CardAuthDetails("0000-1111-2222-3333", "01/25", "123");
		String transactionId = UUID.randomUUID().toString();
		
		TransactionCardAuthDetails transactionCardAuthDetails = new TransactionCardAuthDetails(transactionId, cardAuthDetails);
		
		TransactionToken transactionToken = new TransactionToken(transactionId, buildToken(transactionCardAuthDetails, cipherProvider));
		
		Mockito.when(redisStorage.get(transactionToken.getTransactionId())).thenReturn(cipherProvider.getKeyAsBase64());
		
		CardAuthDetailsValidator validator = new CardAuthDetailsValidator(redisStorage);
		validator.validate(transactionToken);
	}

	private String buildToken(TransactionCardAuthDetails transactionCardAuthDetails, CipherProvider cipherProvider)
	{

		CardAuthDetails cardAuthDetails = transactionCardAuthDetails.getCardAuthDetails();
		String raw = cardAuthDetails.getCardNumber() + "," + cardAuthDetails.getExpirationDate() + "," + cardAuthDetails.getCvv();
		String token = cipherProvider.encryptToBase64String(raw);
		return token;
	}
}
