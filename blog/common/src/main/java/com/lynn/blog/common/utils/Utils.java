package com.lynn.blog.common.utils;

import java.util.UUID;

public final class Utils {

    /**
     * 生成token
     * @param username
     * @param key
     * @return
     */
    public static String generateToken(String username,String key){
        try {
            return AesEncryptUtils.aesEncrypt(username+ System.currentTimeMillis(),key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密密码
     * @param password
     * @param key
     * @return
     */
    public static String encryptPassword(String password,String key){
        try {
            return MessageDigestUtils.encrypt(password+key,Algorithm.SHA1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
