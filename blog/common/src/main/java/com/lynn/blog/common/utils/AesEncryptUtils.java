package com.lynn.blog.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptUtils {

    private static final String KEY = "d7b85f6e214abcda";
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
        String content = "{}";
        System.out.println("加密前：" + content);

        String encrypt = aesEncrypt(content, "d7b85f6e214abcda1111111111111111");
        System.out.println(encrypt.length() + ":加密后：" + encrypt);

        String decrypt = aesDecrypt("0piTdJHsCXm3iHvg98TDgx+Ef5Ww7uxNwhBzUYy1pXtMV5gWQCDMPblNV7ueRPugBOr16va3giy99y664F2LLPTXTxk7H8kFp9YC2hYtHeeAoFGIwIO7TQGXksMCR3U9aHktNQiXGRHedxVyHxgKngeD9mOFpYA2GcP79N+xTA2bZZ3GlqNMFY7jK3mYoDDFZSjSeF4f+VPQ87YOpxMtaw==", "d7b85f6e214abcda");
        System.out.println("解密后：" + decrypt);
    }
}
