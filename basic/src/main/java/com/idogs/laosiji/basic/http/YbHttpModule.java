package com.idogs.laosiji.basic.http;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.aleksandermielczarek.napkin.scope.SessionScope;
import com.idogs.laosiji.basic.http.qualifiers.YbDefaultClient;
import com.idogs.laosiji.basic.http.qualifiers.YbTokenRetrofit;
import com.idogs.laosiji.basic.http.token.TokenServer;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.config.UrlConfig;
import com.idogs.laosiji.core.http.OkHttpModule;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by y on 2017/8/15.
 */
@SessionScope
@Module
public class YbHttpModule extends OkHttpModule {

    /**
     * 请求时自动添加TOKEN
     * 15秒超时
     * @param preference
     * @return
     */
    @Provides
    @SessionScope
    @YbDefaultClient
    public OkHttpClient providerYbDefaultClient(YbAppPreference preference) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .header("access_token",preference.getTokenKey())
                            .build();
                    return chain.proceed(request);
                })
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @SessionScope
    @YbTokenRetrofit
    public Retrofit providerRetrofit(@YbDefaultClient OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
    }

    @Provides
    @SessionScope
    public TokenServer providerTokenServer(@YbTokenRetrofit Retrofit retrofit) {
        return retrofit.create(TokenServer.class);
    }
}
