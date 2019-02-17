package com.mycompany.tokenizer.application.source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.beans.CardAuthDetails;
import com.mycompany.tokenizer.application.source.service.CardAuthSubmitter;

@RestController
public class CardAuthController
{
	private CardAuthSubmitter cardAuthSubmitter;
	
	@Autowired
	public CardAuthController(CardAuthSubmitter cardAuthSubmitter)
	{
		this.cardAuthSubmitter = cardAuthSubmitter;
	}

	@PostMapping(path="/api/auth", consumes="application/json")
	public CardAuthResponse auth(@RequestBody CardAuthDetails cardAuthDetails)
	{
		String trxId = cardAuthSubmitter.submit(cardAuthDetails);
		return new CardAuthResponse(true, trxId);
	}
}
