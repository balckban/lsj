package com.idogs.laosiji.user.http.module;


import com.github.aleksandermielczarek.napkin.scope.UserScope;
import com.idogs.laosiji.basic.http.qualifiers.YbDefaultClient;
import com.idogs.laosiji.config.UrlConfig;
import com.idogs.laosiji.user.http.server.UserServer;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <b>类名称：</b> UserModule <br/>
 * <b>类描述：</b> UserLogin Dagger2 Module<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 13:51:52 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class UserHttpModule {

    @UserScope
    @Provides
    public Retrofit providerUserRetrofit(@YbDefaultClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @UserScope
    @Provides
    public UserServer providerUserServer(Retrofit retrofit) {
        return retrofit.create(UserServer.class);
    }


}

