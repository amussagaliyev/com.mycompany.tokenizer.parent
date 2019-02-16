package com.mycompany.sdk.cipher;

public interface CipherProvider
{
	public byte[] getKey();
	public String getKeyAsBase64();
	
	public byte[] decrypt(byte[] source);
	public byte[] decryptAsBase64(byte[] sourceInBase64);
	public String decryptAsBase64ToString(byte[] sourceInBase64);
	public String decryptAsBase64ToString(String sourceInBase64);
	
	public byte[] encrypt(byte[] source);
	public byte[] encryptToBase64(byte[] source);
	public String encryptToBase64String(byte[] source);
	public String encryptToBase64String(String source);
}
