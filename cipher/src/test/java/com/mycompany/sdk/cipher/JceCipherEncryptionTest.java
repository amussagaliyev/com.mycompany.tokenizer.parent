package com.mycompany.sdk.cipher;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

public class JceCipherEncryptionTest
{
	
	private CipherProvider cipher = new JceCipher();
	private String phraseToEncode = "Encode Me (MyPhrase)!!! @#$%^&*()_+-/\\?><";
	private byte[] phraseToEncodeBytes = phraseToEncode.getBytes();
	
	@Test
	public void givenPhraseBytes_whenEncrypt_Then_ResultNotNull() throws NoSuchAlgorithmException
	{
		byte[] encryptedBytes = cipher.encrypt(phraseToEncodeBytes);
		assertNotNull(encryptedBytes);
		assertTrue(encryptedBytes.length > 0);
	}
	
	@Test
	public void givenPhraseBytes_whenEncryptToBase64_Then_ResultNotNull() throws NoSuchAlgorithmException
	{
		byte[] encryptedBytes = cipher.encryptToBase64(phraseToEncodeBytes);
		assertNotNull(encryptedBytes);
		assertTrue(encryptedBytes.length > 0);
		
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedBytes);
		assertNotNull(decodedBytes);
		assertTrue(decodedBytes.length > 0);
	}
	
	@Test
	public void givenPhraseString_whenEncryptToBase64String_Then_ResultNotNull() throws NoSuchAlgorithmException
	{
		String encryptedString = cipher.encryptToBase64String(phraseToEncode);
		assertNotNull(encryptedString);
		assertTrue(encryptedString.length() > 0);

		byte[] decodedBytes = Base64.getDecoder().decode(encryptedString);
		assertNotNull(decodedBytes);
		assertTrue(decodedBytes.length > 0);
	}
	
	@Test
	public void givenPhraseBytes_whenEncryptToBase64String_Then_ResultNotNull() throws NoSuchAlgorithmException
	{
		String encryptedString = cipher.encryptToBase64String(phraseToEncodeBytes);
		assertNotNull(encryptedString);
		assertTrue(encryptedString.length() > 0);

		byte[] decodedBytes = Base64.getDecoder().decode(encryptedString);
		assertNotNull(decodedBytes);
		assertTrue(decodedBytes.length > 0);
	}
	
}
