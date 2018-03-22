package com.idogs.laosiji.basic.http.token;


import com.idogs.laosiji.basic.model.YbBaseModel;

/**
 * TOKEN实例
 * Created by y on 2017/7/20.
 */

public class YbToken extends YbBaseModel<YbToken> {
    /**
     * TOKEN有效值
     */
    public String access_token;
//    /**
//     * TOKEN记录值，暂无用
//     */
//    public String hash_code;
}
