package com.idogs.laosiji.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;
import com.idogs.laosiji.http.model.IdogsHome;


/**
 * Created by y on 2017/8/24.
 */

public class YbRecResponse extends YbResponse<IdogsHome> {

    public IdogsHome data;

    @Override
    public IdogsHome getResult() {
        return data;
    }
}
