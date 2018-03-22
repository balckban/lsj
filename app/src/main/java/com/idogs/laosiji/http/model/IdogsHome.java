package com.idogs.laosiji.http.model;

import com.idogs.laosiji.basic.model.YbBaseModel;

import java.util.ArrayList;

/**
 * Created by y on 2017/8/1.
 */

public class IdogsHome extends YbBaseModel<IdogsHome> {
    /**
     * header数据
     */

    public ArrayList<IdogHeader> head;

    /**
     * 内容body
     */
    public ArrayList<IdogMovieInfo> movieinfo;

}
