package com.mycompany.sdk.cipher;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt Decrypt util functions with DESede hash key generator algorithm
 * 
 * @author arman
 *
 */
public class JceCipher implements CipherProvider
{
	
	private static final String HASH_ALGO_DESEDE = "DESede";

	private final SecretKey secretKey;
	private final String algorithm;
	
	public JceCipher()
	{
		try
		{
			this.algorithm = HASH_ALGO_DESEDE;
			this.secretKey = KeyGenerator.getInstance(this.algorithm).generateKey();
		} 
		catch (NoSuchAlgorithmException e)
		{
			throw new CipherException("Secret key instantiation exception", e);
		};
	}
	
	public JceCipher(byte[] key)
	{
		this.algorithm = HASH_ALGO_DESEDE;
		this.secretKey = new SecretKeySpec(key, algorithm);;
	}
	
	public JceCipher(String algorithm)
	{
		try
		{
			this.algorithm = algorithm;
			this.secretKey = KeyGenerator.getInstance(this.algorithm).generateKey();
		} 
		catch (NoSuchAlgorithmException e)
		{
			throw new CipherException("Secret key instantiation exception", e);
		};
	}
	
	public JceCipher(String algorithm, byte[] key)
	{
	    this.secretKey = new SecretKeySpec(key, algorithm);
		this.algorithm = algorithm;
	}
	
	@Override
	public byte[] decrypt(byte[] source)
	{
		return doFinal(buildCipher(algorithm, Cipher.DECRYPT_MODE, secretKey), source);
	}

	@Override
	public byte[] decryptAsBase64(byte[] sourceInBase64)
	{
		return decrypt(Base64.getDecoder().decode(sourceInBase64));
	}

	@Override
	public String decryptAsBase64ToString(byte[] sourceInBase64)
	{
		return new String(decryptAsBase64(sourceInBase64));
	}

	@Override
	public String decryptAsBase64ToString(String sourceInBase64)
	{
		return new String(decryptAsBase64(sourceInBase64.getBytes()));
	}

	@Override
	public byte[] encrypt(byte[] source)
	{
		return doFinal(buildCipher(algorithm, Cipher.ENCRYPT_MODE, secretKey), source);
	}

	@Override
	public byte[] encryptToBase64(byte[] source)
	{
		return Base64.getEncoder().encode(encrypt(source));
	}

	@Override
	public String encryptToBase64String(byte[] source)
	{
		return new String(encryptToBase64(source));
	}

	@Override
	public String encryptToBase64String(String source)
	{
		return encryptToBase64String(source.getBytes());
	}
	
	@Override
	public byte[] getKey()
	{
		return secretKey.getEncoded();
	}
	
	@Override
	public String getKeyAsBase64()
	{
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
	
	protected Cipher buildCipher(String algorithm, int cipherMode, SecretKey secretKey)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);
			return cipher;
		}
		catch (Exception e)
		{
			throw new CipherException("Cipher instantiation exception", e);
		}
	}
	
	protected byte[] doFinal(Cipher cipher, byte[] source)
	{
		try
		{
			return cipher.doFinal(source);
		}
		catch (Exception e)
		{
			throw new CipherException("Cipher operation exception", e);
		}
	}
}
