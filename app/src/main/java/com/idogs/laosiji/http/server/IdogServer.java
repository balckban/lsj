package com.idogs.laosiji.http.server;


import com.blankj.utilcode.util.SPUtils;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.http.vo.YbDesResponse;
import com.idogs.laosiji.http.vo.YbFragmentResponse;
import com.idogs.laosiji.http.vo.YbPlayResponse;
import com.idogs.laosiji.http.vo.YbRecResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import zlc.season.rxdownload2.function.Constant;

/**
 * <b>类名称：</b> IdogServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface IdogServer {


    @POST("/movie/indexMovie")
    Single<Response<YbRecResponse>> getlist(@Query("page")Integer page,@Query("size")Integer size,@Query("access_token")String access_token);


    @POST("/movie/movieType")
    Single<Response<YbFragmentResponse>> getclasslist(@Query("type")String type, @Query("page")Integer page, @Query("size")Integer size,@Query("access_token")String access_token);

    @POST("/movie/TvAndMovieInfo")
    Single<Response<YbFragmentResponse>> getTvlist(@Query("types")String types, @Query("page")Integer page, @Query("size")Integer size,@Query("access_token")String access_token);

    @POST("/movie/movieInfo")
    Single<Response<YbDesResponse>> getMovieInfo(@Query("id")String id, @Query("access_token")String access_token);

//    @POST("/playMovie")
//    Single<Response<YbPlayResponse>> getMovieUrl(@Query("id")String id, @Query("access_token")String access_token);
}
