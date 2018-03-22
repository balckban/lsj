package com.idogs.laosiji.user.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 2017/8/24.
 */

public class YbRegisterResponse extends YbResponse<List<String>> {


    public String code;
    public String msg;



    @Override
    public List<String> getResult() {
        List<String> list=new ArrayList<String>();
        list.add(code);
        list.add(msg);
        return list;
    }
}
