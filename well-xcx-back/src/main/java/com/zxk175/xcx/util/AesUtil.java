package com.zxk175.xcx.util;

import cn.hutool.core.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * @author zxk175
 * @since 2018/6/26 11:27
 */
public class AesUtil {

    static {
        // BouncyCastle是一个开源的加解密解决方案，
        // 主页在 http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Aes解密
     *
     * @param data   被加密的数据
     * @param key    秘钥
     * @param iv     偏移量
     * @param encode 解密结果的编码
     * @return 解密结果
     */
    public static String decrypt(String data, String key, String iv, String encode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(Base64.decode(key), "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(Base64.decode(iv)));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(Base64.decode(data));
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, encode);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException("AES解密异常", ex);
        }
    }
}
