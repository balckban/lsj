package com.idogs.laosiji.http.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.github.aleksandermielczarek.napkin.scope.UserScope;
import com.idogs.laosiji.basic.http.qualifiers.YbDefaultClient;
import com.idogs.laosiji.config.UrlConfig;
import com.idogs.laosiji.core.http.qualifiers.NoCacheClient;
import com.idogs.laosiji.http.server.IdogServer;
import com.idogs.laosiji.update.http.server.FirServer;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <b>类名称：</b> IdogsRetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 13:55<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class IdogsRetrofitModule {

    @ActivityScope
    @Provides
    public Retrofit providerIdogsRetrofit(@YbDefaultClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScope
    @Provides
    public IdogServer providerIdogsServer(Retrofit retrofit) {
        return retrofit.create(IdogServer.class);
    }


}
