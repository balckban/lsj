package com.idogs.laosiji.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;
import com.idogs.laosiji.http.model.IdogMovieInfo;


/**
 * Created by y on 2017/8/24.
 */

public class YbPlayResponse extends YbResponse<String> {

    public String code;


    @Override
    public String getResult() {
        return code;
    }
}
