package com.idogs.laosiji.user.http.server;


import com.idogs.laosiji.user.http.vo.YbLoginResponse;
import com.idogs.laosiji.user.http.vo.YbRegisterResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <b>类名称：</b> UserServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserServer {

    /**
     * 登录接口
     * @return
     */
    @Headers("Authorization:Basic bGFvc2lqaTpsYW9zaWpp")
    @POST("/oauth/token")
    Single<Response<YbLoginResponse>> Login (@Query("username")String username, @Query("password") String password,@Query("grant_type") String grant_type);

    @POST("/user/register")
    Single<Response<YbRegisterResponse>> Register(@Query("username")String username, @Query("password") String password);
}
