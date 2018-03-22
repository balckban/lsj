package com.idogs.laosiji.basic.http.token;


import com.idogs.laosiji.basic.http.YbResponse;

/**
 * Created by y on 2017/7/20.
 */

public class YbTokenResponse extends YbResponse<YbToken> {

    /**
     * TOKEN值
     */
    public YbToken tokenKV;

    @Override
    public YbToken getResult() {
        return tokenKV;
    }
}
