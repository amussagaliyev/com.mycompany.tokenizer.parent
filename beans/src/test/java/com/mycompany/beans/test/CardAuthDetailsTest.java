package com.mycompany.beans.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.beans.CardAuthDetails;

public class CardAuthDetailsTest
{
	
	private CardAuthDetails cad;
	
	@BeforeEach
	public void init()
	{
		cad = new CardAuthDetails();
	}
	
	@Test
	public void cardNumber_getter_setter()
	{
		String cardNumber = "0000-1111-2222-3333";
		cad.setCardNumber(cardNumber);
		assertTrue(cardNumber.equals(cad.getCardNumber()));
	}

	@Test
	public void expirationDate_getter_setter()
	{
		String expirationDate = "01/25";
		cad.setExpirationDate(expirationDate);
		assertTrue(expirationDate.equals(cad.getExpirationDate()));
	}

	@Test
	public void cvv_getter_setter()
	{
		String cvv = "123";
		cad.setCvv(cvv);
		assertTrue(cvv.equals(cad.getCvv()));
	}
}
