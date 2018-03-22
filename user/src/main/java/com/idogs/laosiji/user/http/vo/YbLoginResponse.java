package com.idogs.laosiji.user.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 2017/8/24.
 */

public class YbLoginResponse extends YbResponse<List<String>> {


    public String access_token;
    public String refresh_token;
    public String expires_in;


    @Override
    public List<String> getResult() {
        List<String> list=new ArrayList<String>();
        list.add(access_token);
        list.add(refresh_token);
        list.add(expires_in);
        return list;
    }
}
