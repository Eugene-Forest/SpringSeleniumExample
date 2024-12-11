package org.tutor.auth.verify;

import org.tutor.auth.common.HttpRequestHelper;
import org.tutor.auth.entity.AccountSignature;
import org.tutor.auth.entity.SimpleSignature;

import javax.servlet.http.HttpServletRequest;

/**
 * 网络请求校验类
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class DefaultVerifySign {

    public static boolean verifySimple(String dateTime, String sign){

        return false;
    }

    public static SimpleSignature defaultVerify(HttpServletRequest request){
        String sign = request.getParameter("sign");
        String dataTime = request.getParameter("dataTime");
        SimpleSignature signature = new SimpleSignature();
        signature.setSign(sign);
        signature.setDateTime(dataTime);
        signature.setVerify(true);
        return signature;
    }

    public static AccountSignature accountVerify(HttpServletRequest request) {
        String sign = request.getParameter("sign");
        String app = request.getParameter("app");
        String dataTime = request.getParameter("dataTime");
        String bodyString = HttpRequestHelper.getBodyString(request);
        String dbName = request.getParameter("dbName");
        String user = request.getParameter("user");
        if (dbName == null || dbName.isEmpty()) {
            //如果没有指定数据库
            dbName = "default";
        }
        if (app == null || app.isEmpty()) {
            app = "default";
        }
        if (user == null || user.isEmpty()) {
            user = "default";
        }
        if (sign == null || dataTime == null) {
            AccountSignature accountSignature = new AccountSignature();
            accountSignature.setVerify(false);
            return accountSignature;
        }
        //签名认证
        AccountSignature accountSignature = new AccountSignature();
        accountSignature.setVerify(true);
        accountSignature.setDbName(dbName);
        accountSignature.setSign(sign);
        accountSignature.setDateTime(dataTime);
        accountSignature.setBodyString(bodyString);
        accountSignature.setUser(user);

        return accountSignature;
    }
}
