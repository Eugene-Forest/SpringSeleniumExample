package org.tutor.sign.entity;

/**
 * 简单签名
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class SimpleSignature {
    /** 签名 */
    private String signature;
    /** 时间戳 */
    private String dateTime;

    private boolean isVerify;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
