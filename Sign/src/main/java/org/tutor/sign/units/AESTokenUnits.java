package org.tutor.sign.units;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * 对称加密算法工具类
 *
 * @author Eugene-Forest
 * {@code @date} 2024/11/28
 */
public class AESTokenUnits {

    private static Logger log = LoggerFactory.getLogger(AESTokenUnits.class);


    /**
     * （伪）随机数生成器生成秘密密钥
     *
     * @param n AES密钥的大小 128、192、256
     * @return
     */
    public static String generateKey(int n) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(n, new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            log.debug("keyBytes length: "+ keyBytes.length);
            return Base64.encodeBase64String(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 基于密码的密钥派生功能
     *
     * @param password
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
    }

    /**
     * 字符串加密
     *
     * @param sSrc
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, String key) {
        try {
            String sKey = Base64.encodeBase64String(key.getBytes());
            if (sKey == null) {
                log.error("encrypt - Key为空null");
                return null;
            }
//            if (sKey.length() != 16) {
//                log.error("encrypt - Key长度不是16位");
//                return null;
//            }
            byte[] raw = sKey.getBytes();
            log.debug("key length: "+ raw.length);
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            SecretKey skeySpec = new SecretKeySpec(raw, 0, raw.length,"AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 字符串解密
     *
     * @param sSrc
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc, String key) {
        try {
            String sKey = Base64.encodeBase64String(key.getBytes());
            if (sKey == null) {
                log.error("decrypt - Key为空null");
                return null;
            }
//            if (sKey.length() != 16) {
//                log.error("decrypt - Key长度不是16位");
//                return null;
//            }
            byte[] raw = sKey.getBytes();
            SecretKey skeySpec = new SecretKeySpec(raw, 0, raw.length,"AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decodeBase64(sSrc.getBytes(StandardCharsets.UTF_8));
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
