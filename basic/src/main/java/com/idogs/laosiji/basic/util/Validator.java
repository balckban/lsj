package com.idogs.laosiji.basic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查器
 * Created by y on 2017/7/29.
 */

public class Validator {

    /**
     * 验证手机号码
     *
     * @param phone
     * @return
     */
    public static String checkPhone(String phone) {
        if (phone == null || phone.equals(""))
            return TipUtil.userPhoneNumberIsEmpty;
        Pattern p = Pattern.compile("^((1[0-9]))\\d{9}$");
        Matcher m = p.matcher(phone);
        if (m.matches())
            return null;
        else
            return TipUtil.userWrongPhoneNumber;
    }
    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    public static String checkPassWord(String password) {
        if (password == null || password.equals(""))
            return TipUtil.passwordIsEmpty;
        return null;
    }

    /**
     * 验证短信验证码
     *
     * @param yzcode
     * @return
     */
    public static String checkYzCode(String yzcode) {
        if (yzcode == null || yzcode.equals(""))
            return TipUtil.yzcodeIsNotNull;

        if (yzcode.length() > 6 || yzcode.length()< 4)
            return TipUtil.yzcodeWrongLength;

        Matcher m=Pattern.compile("^[A-Za-z0-9]+$").matcher(yzcode);
        if (m.matches())
            return null;
        else
            return TipUtil.yzcodeWrongChar;
    }
}
