package org.tutor.sign.units;

import org.tutor.sign.entity.SignKey;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class SignKeyUnits {
    public static final String RSA = "RSA";
    public static final String DSA = "DSA";

    public static SignKey createSignKey(String algorithm, int keySize){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            Base64.Encoder encoder = Base64.getEncoder();
            //初始化密匙长度
            keyPairGenerator.initialize(keySize);
            //生成密匙对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //得到公匙
            Key publicKey = keyPair.getPublic();
            //得到密匙
            Key privateKey = keyPair.getPrivate();
            String privateKeyString = encoder.encodeToString(privateKey.getEncoded());
            String publicKeyString = encoder.encodeToString(publicKey.getEncoded());
            //创建密匙对对象
            SignKey signKey = new SignKey();
            signKey.setPrivateKey(privateKeyString);
            signKey.setPublicKey(publicKeyString);
            return signKey;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm: " + algorithm);
        } catch (InvalidParameterException e){
            throw new IllegalArgumentException("Invalid key size: " + keySize + " for " + algorithm);
        }
    }

    public static SignKey createSignKeyByRSA(){
        return createSignKey(RSA, 1024);
    }

    public static SignKey createSignKeyByDSA(){
        return createSignKey(DSA, 1024);
    }

    public static boolean verify(String data, String sign, RSAPublicKey publicKey){
        byte[] keyBytes = publicKey.getEncoded();
        return false;
    }
}
