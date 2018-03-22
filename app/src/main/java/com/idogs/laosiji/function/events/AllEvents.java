package com.idogs.laosiji.function.events;

import com.idogs.laosiji.http.model.IdogMovieInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class AllEvents {
    private IdogMovieInfo idogMovieInfo;

    public AllEvents(IdogMovieInfo idogMovieInfo) {
        this.idogMovieInfo = idogMovieInfo;
    }



    public IdogMovieInfo getMovieInfo() {
        return idogMovieInfo;
    }

    public void setMovieInfo(IdogMovieInfo idogMovieInfo) {
        this.idogMovieInfo = idogMovieInfo;
    }


    private ArrayList<IdogMovieInfo> listMovieInfo;

    public AllEvents(ArrayList<IdogMovieInfo>  listMovieInfo) {
        this.listMovieInfo = listMovieInfo;
    }



    public ArrayList<IdogMovieInfo>  getListMovieInfo() {
        return listMovieInfo;
    }

    public void setMovieInfo(ArrayList<IdogMovieInfo>  listMovieInfo) {
        this.listMovieInfo = listMovieInfo;
    }
}
