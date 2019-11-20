package com.azkj.houseproperty.common.utils;

import com.azkj.houseproperty.common.constants.Constants;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLEncoder;

public class MiaodiUtil {
    public static void   execute(String phone,String number) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("accountSid").append("=").append(Constants.ACCOUNT_SID);
        sb.append("&to").append("=").append(phone);
        sb.append("&param").append("=").append(URLEncoder.encode(number,"UTF-8"));
        sb.append("&templateid").append("=").append("217017");
//		sb.append("&smsContent").append("=").append( URLEncoder.encode("【秒嘀科技】您的验证码为123456，该验证码5分钟内有效。请勿泄漏于他人。","UTF-8"));
        String body = sb.toString() + createCommonParam(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);
        String result = HttpUtil.post(Constants.BASE_URL, body);
        System.out.println(result);

    }

    public static String createCommonParam(String sid,String token) {
        // 时间戳
        long timestamp = System.currentTimeMillis();
        // 签名
        String sig = DigestUtils.md5Hex(sid + token + timestamp);

        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + Constants.RESP_DATA_TYPE;
    }
}
