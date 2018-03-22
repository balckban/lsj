package com.idogs.laosiji.http.vo;

import com.idogs.laosiji.basic.http.YbResponse;
import com.idogs.laosiji.http.model.IdogMovieInfo;

import java.util.ArrayList;


/**
 * Created by y on 2017/8/24.
 */

public class YbFragmentResponse extends YbResponse<ArrayList<IdogMovieInfo>> {

    public ArrayList<IdogMovieInfo> data;

    @Override
    public ArrayList<IdogMovieInfo> getResult() {
        return data;
    }
}
