package com.idogs.laosiji.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.idogs.laosiji.http.model.IdogsHome;


/**
 * Created by y on 2017/8/24.
 */

public class YbDesResponse extends YbResponse<IdogMovieInfo> {

    public String code;
    public String msg;
    public IdogMovieInfo data;

    @Override
    public IdogMovieInfo getResult() {
        return data;
    }
}
