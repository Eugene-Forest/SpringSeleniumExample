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

//    RSA 算法的执行速度较慢，尤其是对于大型密钥和数据。
//    因此，在实际应用中，通常使用 RSA 算法来加密对称密钥（如 AES），然后使用对称加密算法加密实际的数据，以提高效率。

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


    public static boolean verify(String data, String sign, RSAPublicKey publicKey){
        byte[] keyBytes = publicKey.getEncoded();
        return false;
    }

    /**
     *  RSA 公匙加密
     */
    public static void encode(){

    }
}
