package org.tutor.sign.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tutor.sign.entity.SignKey;
import org.tutor.sign.units.FileUnits;
import org.tutor.sign.units.SignKeyUnits;

import java.io.File;
import java.io.IOException;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class SignKeyCommon {
    private static final String KeyFileBase = "static/";
    private static final String KeyFileRSA = KeyFileBase + "rsa/";
    private static final String KeyFileDSA = KeyFileBase + "dsa/";

    private static Logger log = LoggerFactory.getLogger(SignKeyCommon.class);

    public static boolean createSignKey(String name) {
        try {
            String privateKeyFilePath = getPrivateKeyFileSavePath(name);
            File privateKeyFile = new File(privateKeyFilePath);
            if(privateKeyFile.createNewFile()){
                log.warn("Private key file created");
            }else if(privateKeyFile.exists()) {
                log.warn("Private key file already exists");
                return false;
            }
            String publicKeyFilePath = getPublicKeyFileSavePath(name);
            File publicKeyFile = new File(publicKeyFilePath);
            if(publicKeyFile.createNewFile()){
                log.warn("Public key file created");
            } else if(publicKeyFile.exists()) {
                log.warn("Public key file already exists");
                return false;
            }
            SignKey signKey = SignKeyUnits.createSignKeyByRSA();
            FileUnits.writeTextFile(privateKeyFile, signKey.getPrivateKey());
            FileUnits.writeTextFile(publicKeyFile, signKey.getPublicKey());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    public static String getPrivateKeyFileSavePath(String name) {
        return KeyFileRSA + name + ".private.pfx";
    }

    public static String getPublicKeyFileSavePath(String name) {
        return KeyFileRSA + name + ".public.cer";
    }
}
