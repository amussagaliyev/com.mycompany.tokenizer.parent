package com.mycompany.tokenizer.application.source.test;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.tokenizer.application.source.controller.CardAuthController;
import com.mycompany.tokenizer.application.source.controller.CardAuthResponse;
import com.mycompany.tokenizer.application.source.service.CardAuthSubmitter;
import static org.junit.jupiter.api.Assertions.*;

public class CardAuthControllerTest
{
	@BeforeEach
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	public CardAuthSubmitter cardAuthSubmitter;

	@Test
	public void authTest()
	{
		CardAuthDetails cardAuthDetails = new CardAuthDetails();
		Mockito.when(cardAuthSubmitter.submit(cardAuthDetails)).thenReturn(UUID.randomUUID().toString());
		CardAuthController controller = new CardAuthController(cardAuthSubmitter);
		CardAuthResponse resp = controller.auth(cardAuthDetails);
		
		assertNotNull(resp);
		assertNotNull(resp.getTransactionId());
		assertTrue(resp.isSuccess());

	}
}
