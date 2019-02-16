package com.mycompany.sdk.cipher;

import java.util.Base64;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JceCipherDecryptionTest
{
	private String keyBase64 = "JYlMs/QH4DvlDauP6b/3GsfmRf6o6pTg";
	private String originalPhrase = "Encode Me (MyPhrase)!!! @#$%^&*()_+-/\\?><";
	private String encryptedAndBase64DecodedPhrase = "ikUsrBiHs2iLgr5UwHtvYEo3ALuQM/e+yULyOSAnd/1iqpG9seYPf36JxLCn40/R";
	
	@Test
	public void givenKey_And_encryptedPhrase_whenDecrypt_Then_ResultNotNull_And_EqualsToOriginalPhrase()
	{
		CipherProvider cipher = new JceCipher(Base64.getDecoder().decode(keyBase64));
		byte[] decryptedBytes = cipher.decrypt(Base64.getDecoder().decode(encryptedAndBase64DecodedPhrase));
		assertNotNull(decryptedBytes);
		assertTrue(decryptedBytes.length > 0);
		assertTrue(originalPhrase.equals(new String(decryptedBytes)));
	}
	
	@Test
	public void givenKey_And_encryptedPhrase_whenDecryptAsBase64_Then_ResultNotNull_And_EqualsToOriginalPhrase()
	{
		CipherProvider cipher = new JceCipher(Base64.getDecoder().decode(keyBase64));
		byte[] decryptedBytes = cipher.decryptAsBase64(encryptedAndBase64DecodedPhrase.getBytes());
		assertNotNull(decryptedBytes);
		assertTrue(decryptedBytes.length > 0);
		assertTrue(originalPhrase.equals(new String(decryptedBytes)));
	}
	
	@Test
	public void givenKey_And_encryptedPhrase_whenDecryptAsBase64ToString_Then_ResultNotNull_And_EqualsToOriginalPhrase()
	{
		CipherProvider cipher = new JceCipher(Base64.getDecoder().decode(keyBase64));
		String decryptedPhrase = cipher.decryptAsBase64ToString(encryptedAndBase64DecodedPhrase.getBytes());
		assertNotNull(decryptedPhrase);
		assertTrue(decryptedPhrase.length() > 0);
		assertTrue(originalPhrase.equals(decryptedPhrase));
	}
	
	@Test
	public void givenKey_And_encryptedPhraseString_whenDecryptAsBase64ToString_Then_ResultNotNull_And_EqualsToOriginalPhrase()
	{
		CipherProvider cipher = new JceCipher(Base64.getDecoder().decode(keyBase64));
		String decryptedPhrase = cipher.decryptAsBase64ToString(encryptedAndBase64DecodedPhrase);
		assertNotNull(decryptedPhrase);
		assertTrue(decryptedPhrase.length() > 0);
		assertTrue(originalPhrase.equals(decryptedPhrase));
	}
	
	@Test
	public void givenOriginalPhrase_whenEncryptToBase64String_And_DecryptAsBase64ToString_Then_ResultNotNull_And_EqualsToOriginalPhrase()
	{
		CipherProvider cipherToEncrypt = new JceCipher();
		String encryptedPhrase = cipherToEncrypt.encryptToBase64String(originalPhrase);
		byte[] key = cipherToEncrypt.getKey();
		CipherProvider cipherToDecrypt = new JceCipher(key);
		String decryptedPhrase = cipherToDecrypt.decryptAsBase64ToString(encryptedPhrase);
		
		assertNotNull(decryptedPhrase);
		assertTrue(decryptedPhrase.length() > 0);
		assertTrue(originalPhrase.equals(decryptedPhrase));
	}
	
}
