package com.zxk175.xcx.bean;

import lombok.Data;

/**
 * @author zxk175
 * @since 2020-03-18 10:03
 */
@Data
public class WxXcxDecodePhoneParam {

    private String iv;

    private String encryptedData;

}
