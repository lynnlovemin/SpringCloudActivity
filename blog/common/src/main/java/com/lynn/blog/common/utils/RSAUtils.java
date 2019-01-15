package com.lynn.blog.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>(2);
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    public static void main(String[] args) throws Exception{
//        String serverPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJxmWQdpI3R/DcJYaDNy4944o900od1zadHootdrOGHMWF7vw2oyuGzI1N/frmxoVLaUAMrcLMBLMfhPRtP4acnvuOgM4/7RKq5scrAAi/znSVPRDFL5165QeDb64diF2EjDk0KZnRKQ1qXDyKA/XJL7ZMQhhqfeVqQ8G9khKpGzAgMBAAECgYEAj+5AkGlZj6Q9bVUez/ozahaF9tSxAbNs9xg4hDbQNHByAyxzkhALWVGZVk3rnyiEjWG3OPlW1cBdxD5w2DIMZ6oeyNPA4nehYrf42duk6AI//vd3GsdJa6Dtf2has1R+0uFrq9MRhfRunAf0w6Z9zNbiPNSd9VzKjjSvcX7OTsECQQD20kekMToC6LZaZPr1p05TLUTzXHvTcCllSeXWLsjVyn0AAME17FJRcL9VXQuSUK7PQ5Lf5+OpjrCRYsIvuZg9AkEAojdC6k3SqGnbtftLfGHMDn1fe0nTJmL05emwXgJvwToUBdytvgbTtqs0MsnuaOxMIMrBtpbhS6JiB5Idb7GArwJAfKTkmP5jFWT/8dZdBgFfhJGv6FYkEjrqLMSM1QT7VzvStFWtPNYDHC2b8jfyyAkGvpSZb4ljZxUwBbuh5QgM4QJBAJDrV7+lOP62W9APqdd8M2X6gbPON3JC09EW3jaObLKupTa7eQicZsX5249IMdLQ0A43tanez3XXo0ZqNhwT8wcCQQDUubpNLwgAwN2X7kW1btQtvZW47o9CbCv+zFKJYms5WLrVpotjkrCgPeuloDAjxeHNARX8ZTVDxls6KrjLH3lT";
//        String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcZlkHaSN0fw3CWGgzcuPeOKPdNKHdc2nR6KLXazhhzFhe78NqMrhsyNTf365saFS2lADK3CzASzH4T0bT+GnJ77joDOP+0SqubHKwAIv850lT0QxS+deuUHg2+uHYhdhIw5NCmZ0SkNalw8igP1yS+2TEIYan3lakPBvZISqRswIDAQAB";
//        String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzVYzTAk2RjNlFWWi5kpUlmHBtsNekfcWedozWohJS/SmO5uHBNfYoFGwMn9u8u6Bivp3wAWdrr2ICuBvnp0oeC078Y3F/uceElfIS1ooPW6JRC2N6hBZ0mvxWtRa50CNkL8tMZ+/9h+YuCJpu4qd+nWjRZnWmrygwMYWMQM7hfwIDAQAB";
//        String clientPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALNVjNMCTZGM2UVZaLmSlSWYcG2w16R9xZ52jNaiElL9KY7m4cE19igUbAyf27y7oGK+nfABZ2uvYgK4G+enSh4LTvxjcX+5x4SV8hLWig9bolELY3qEFnSa/Fa1FrnQI2Qvy0xn7/2H5i4Imm7ip36daNFmdaavKDAxhYxAzuF/AgMBAAECgYAAnZ2TJ+HeSw5/53Otl2w9qc1RAxdqcCgaiCupnPrVIBthazeBM086Cf/o9y+I8Vf2ntLp/Qc+3mZGvh0SaYeem063W41+hnGXTEkzAhSpKdmL6qt3CVlqKIKURdSweMyOXSfSOaxh1JMbg8yfFt9B/8F8InDOcIwP9eQ1n+89MQJBAO1hWa0n5YXiqlPFCNRiKUGFrxnlmwMP5LgG1DkObUUUj/VIAhAPLS02gacsqIGnpL+UlIDeCT+lvE0frvoGT4kCQQDBZp7+ItRMyvpBnDTs6i5WvnZ2LWzMvk37Jq5kLqOYre65u774wGIJ+Ybwoj1ItgrGhdsh9i/OgwNOBJDhrR7HAkBkYAXLclNbibz8LxTMubU4P48LJEunv9C3ACw89BzAWTGGRiyZHdG8jI/GYNvf90NyvyEMXNcMUsiM1BjpIEIBAkEAi5YKqPfwbKCPcS1SEbCP03HSREdS4I917xAjDBGRaBAZXhqAoj8QQ4zCdQlT3BfvvQs9dsyeApoSl4u6Gff+XwJBALBuzOoUTmPvETu8zmIJTPr4/qXajDzYdwR8YmUbb2VZ/03lbHq0nRDgm/RPIfbBzkU3fSmVO4e8QEfUvIy506Q=";
//        String clientEncryptPublicKey = "DMOLyy4ck+SUpyuBp1kfpzV1A4Z5zhlTFedwX0ObvLccvIh+ZZyxZv1NUsdI21/+nq5Kx5NF3vkiEOqmhXbNJoiHmrOGkInXVIy+Yhkz9abugJW37yZeFJV9Mv9mNhHtjcsTkayb4lna4wGTXNvb2fB+36cNSBi+t8JOsFD3LV2ZDPf8yqYOeLm19H7GybC9EyzTC12zunt29KgF7MDSRnT6fdnv4aWUnN5/P01G7ATSJRi20cHw59fM8CmvxgjIoIKEEdG0wc1VFT0JO5hp4x8PZbBCc9U6CnxLJBqUwBA+sM7WdjJN9VoaTuKIjiiRQjhdDK+1K3Xw9AkKYAPgow==";
//        System.out.println(RSAUtils.publicEncrypt(clientPublicKey,RSAUtils.getPublicKey(serverPublicKey)));
//        System.out.println(RSAUtils.privateDecrypt(clientEncryptPublicKey,RSAUtils.getPrivateKey(serverPrivateKey)));
        String serverPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJxmWQdpI3R/DcJYaDNy4944o900od1zadHootdrOGHMWF7vw2oyuGzI1N/frmxoVLaUAMrcLMBLMfhPRtP4acnvuOgM4/7RKq5scrAAi/znSVPRDFL5165QeDb64diF2EjDk0KZnRKQ1qXDyKA/XJL7ZMQhhqfeVqQ8G9khKpGzAgMBAAECgYEAj+5AkGlZj6Q9bVUez/ozahaF9tSxAbNs9xg4hDbQNHByAyxzkhALWVGZVk3rnyiEjWG3OPlW1cBdxD5w2DIMZ6oeyNPA4nehYrf42duk6AI//vd3GsdJa6Dtf2has1R+0uFrq9MRhfRunAf0w6Z9zNbiPNSd9VzKjjSvcX7OTsECQQD20kekMToC6LZaZPr1p05TLUTzXHvTcCllSeXWLsjVyn0AAME17FJRcL9VXQuSUK7PQ5Lf5+OpjrCRYsIvuZg9AkEAojdC6k3SqGnbtftLfGHMDn1fe0nTJmL05emwXgJvwToUBdytvgbTtqs0MsnuaOxMIMrBtpbhS6JiB5Idb7GArwJAfKTkmP5jFWT/8dZdBgFfhJGv6FYkEjrqLMSM1QT7VzvStFWtPNYDHC2b8jfyyAkGvpSZb4ljZxUwBbuh5QgM4QJBAJDrV7+lOP62W9APqdd8M2X6gbPON3JC09EW3jaObLKupTa7eQicZsX5249IMdLQ0A43tanez3XXo0ZqNhwT8wcCQQDUubpNLwgAwN2X7kW1btQtvZW47o9CbCv+zFKJYms5WLrVpotjkrCgPeuloDAjxeHNARX8ZTVDxls6KrjLH3lT";
        String d = RSAUtils.privateDecrypt("LSiNFXigYAIIFBrsWocfO0MeYSWWK+PejLnjvvp/kLDiQoJigJ2QevMoJVRdPMmG4yxHgXS/AJl3gBLvUxgUSTCWb0mHKAUBmPZ4QfRrtTcCmVtFnS8/hUrl8O0b03YzH+7+Pdo0ZeT/9D/9KcR07jGo5+1Rp5MHMnbPlKZeNAsyOX2gpGHgJ7pRPYQFmb6C31KNtnQTNmjuwkryg1TKm1lhxcuD27RampAxTmTUjdDoOKn2ViMucC8rv98853u8dbPIn32+e07LbIh/eht6dYTp8sMa+Pne+Z76WFcOmSuzOCFgCDH76W+rWfep0h8tb90XBXExnFOlbEZ8fsMAfA==",RSAUtils.getPrivateKey(serverPrivateKey));
        System.out.println(d);
    }
}
