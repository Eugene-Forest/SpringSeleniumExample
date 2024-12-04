import org.tutor.sign.common.SignKeyCommon;
import org.tutor.sign.units.SignKeyUnits;

/**
 * 此用例需要Sign项目为Root，才能正常搜索到密匙文件（搜索文件的逻辑是相对目录），主要根据Idea打开的项目的根目录为准
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class TestSignKeyMain {
    public static void main(String[] args) {
//        createTestKey();
//        simpleSign();
        encryptData();
    }

    /**
     * 测试创建测试用密匙对
     */
    public static void createTestKey(){
        SignKeyCommon.createSignKey("TestKey");
    }

    /**
     * 简单的数据签名和验签机制测试
     */
    public static void simpleSign(){
        //数据
        String data = "hello world";
        //签名
        String sign = SignKeyUnits.signWithRSA(data);

        //然后根据公匙进行数据的验签
        System.out.println(SignKeyUnits.verifySignWithRSA("hello world!", sign));//false
        System.out.println(SignKeyUnits.verifySignWithRSA("hello world", sign));//true

        // 思考：
        // 通过用例可以知道，签名和验签的机制可以保证数据的准确
        // 但是此时依旧有一个问题，这种验签模式说明我们在发起网络请求的时候会明文发送请求内容，容易泄露信息
        // 所以，签名机制还可以从加密请求内容的角度进行优化。
    }

    public static void encryptData(){
        String data = "hello world";
        String encryptedData = SignKeyUnits.encryptWithRSA(data);
        System.out.println("加密后数据: " + encryptedData);
        String message = SignKeyUnits.decryptWithRSA(encryptedData);
        System.out.println("解密后数据: " + message);
        System.out.println(data.equals(message));
    }
}
