package org.tutor.auth.units;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.tutor.auth.exception.AESException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加密算法工具类
 *
 * @author Eugene-Forest
 * {@code @date} 2024/11/28
 */
public class AESUnits {

    private static Logger log = LoggerFactory.getLogger(AESUnits.class);
    private static final String ENCODING = "UTF-8";
    private static final String PASSWORD = "46EBA22EF5204DD5B110A1F730513965"; // 加密秘钥

    /**
     * （伪）随机数生成器生成 Base64密钥
     *
     * @param n AES密钥的大小 128、192、256
     * @return
     */
    public static String generateKey(int n) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(n);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            log.debug("keyBytes length: " + keyBytes.length);
            return Base64.encodeBase64String(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 字符串加密
     *
     * @param sSrc 加密字符串
     * @param key Base64密钥
     * @return
     * @throws Exception
     */
    public static String encryptWithByteKey(String sSrc, String key) {
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
            log.debug("key length: " + raw.length);
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            SecretKey skeySpec = new SecretKeySpec(raw, 0, raw.length, "AES");

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
     * @param sSrc 解密字符串
     * @param key Base64密钥
     * @return
     * @throws Exception
     */
    public static String decryptWithByteKey(String sSrc, String key) {
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
            SecretKey skeySpec = new SecretKeySpec(raw, 0, raw.length, "AES");
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

    /**
     * AES加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String encryptAES(String content) throws AESException {
        if (StringUtils.isEmpty(content)) {
            throw new AESException("明文不能为空！");
        }
        byte[] encryptResult = encrypt(content, PASSWORD);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        // BASE64位加密
        encryptResultStr = ebotongEncrypto(encryptResultStr);
        return encryptResultStr;
    }

    /**
     * AES解密
     *
     * @param encryptResultStr 密文
     * @return 明文
     */
    public static String decryptAES(String encryptResultStr) throws AESException {
        if (StringUtils.isEmpty(encryptResultStr)) {
            throw new AESException("密文不能为空");
        }
        // BASE64位解密
        try {
            String decrpt = ebotongDecrypto(encryptResultStr);
            byte[] decryptFrom = parseHexStr2Byte(decrpt);
            byte[] decryptResult = decrypt(decryptFrom, PASSWORD);
            return new String(decryptResult);
        } catch (Exception e) { // 当密文不规范时会报错，可忽略，但调用的地方需要考虑
            return null;
        }
    }

    /**
     * 加密字符串
     */
    private static String ebotongEncrypto(String str) {
        String result = str;
        if (str != null && str.length() > 0) {
            try {
                byte[] encodeByte = str.getBytes(ENCODING);
                result = Base64.encodeBase64String(encodeByte);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // base64加密超过一定长度会自动换行 需要去除换行符
        return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 解密字符串
     */
    private static String ebotongDecrypto(String str) {
        try {
            byte[] encodeByte = Base64.decodeBase64(str.getBytes(ENCODING));
            return new String(encodeByte);
        } catch (IOException e) {
            log.error("IO 异常", e);
            return str;
        }
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    private static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 注意这句是关键，防止linux下 随机生成key。用其他方式在Windows上正常，但Linux上会有问题
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            // kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            log.error("加密异常", e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    private static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            // kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            log.error("解密异常", e);
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
