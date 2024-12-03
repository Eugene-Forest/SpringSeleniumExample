package org.tutor.sign.units;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tutor.sign.common.SignKeyCommon;
import org.tutor.sign.entity.SignKey;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class SignKeyUnits {

    public static final String RSA = "RSA";

    private static Logger log = LoggerFactory.getLogger(SignKeyUnits.class);

//    RSA 算法的执行速度较慢，尤其是对于大型密钥和数据。
//    因此，在实际应用中，通常使用 RSA 算法来加密对称密钥（如 AES），然后使用对称加密算法加密实际的数据，以提高效率。

    public static SignKey createSignKey(String algorithm, int keySize) {
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
        } catch (InvalidParameterException e) {
            throw new IllegalArgumentException("Invalid key size: " + keySize + " for " + algorithm);
        }
    }

    public static SignKey createSignKeyByRSA() {
        return createSignKey(RSA, 1024);
    }

    public static boolean verify(String data, String sign, RSAPublicKey publicKey) {
        byte[] keyBytes = publicKey.getEncoded();
        return false;
    }

    /**
     * RSA 签名
     * @param data 被签名数据
     * @return 签名
     */
    public static String signRSA(String data) {
        try {
            PrivateKey privateKey = SignKeyCommon.getPrivateKey("TestKey", RSA);
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("signRSA 使用了非法加密算法！");
        } catch (InvalidKeyException e) {
            log.error("Invalid key: " + e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid signature: " + e.getMessage());
        }
        return null;
    }

    /**
     * RSA 验签
     *
     * @param data 验签内容
     * @param sign 签名
     * @return 验签结果
     */
    public static boolean verifySingRSA(String data, String sign) {
        try {
            PublicKey publicKey = SignKeyCommon.getPublicKey("TestKey", RSA);
            if (publicKey == null) {
                log.warn("Public key is null");
                return false;
            }
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signBytes = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8));
            return signature.verify(signBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("verifySingRSA 使用了非法加密算法！");
        } catch (InvalidKeyException e) {
            log.error("Invalid key: " + e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid signature: " + e.getMessage());
        }
        return false;
    }
}
