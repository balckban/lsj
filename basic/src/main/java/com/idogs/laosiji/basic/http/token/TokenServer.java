package com.idogs.laosiji.basic.http.token;


import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit 所需要定义的基本接口类，具体业务接口继承自该接口
 * Created by y on 2017/7/19.
 */

public interface TokenServer {

    /**
     * 根据过期TOKEN去获取TOKEN
     * @param deviceType 设备类型
     * @return
     */
    @POST("/user/getTokenKey")
    Single<Response<YbTokenResponse>> getToken(@Query("deviceType") String deviceType);
}
