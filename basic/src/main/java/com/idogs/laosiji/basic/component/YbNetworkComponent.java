package com.idogs.laosiji.basic.component;

import com.github.aleksandermielczarek.napkin.scope.SessionScope;
import com.idogs.laosiji.basic.http.YbHttpModule;
import com.idogs.laosiji.basic.http.qualifiers.YbDefaultClient;
import com.idogs.laosiji.basic.http.qualifiers.YbTokenRetrofit;
import com.idogs.laosiji.basic.http.token.TokenServer;
import com.idogs.laosiji.basic.http.token.YbTokenObersver;
import com.idogs.laosiji.core.component.NetComponent;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by y on 2017/8/15.
 */
@SessionScope
@Component(dependencies = YbBasicComponent.class, modules = {YbHttpModule.class})
public interface YbNetworkComponent extends NetComponent {

    @YbDefaultClient
    OkHttpClient ybDefaultClient();

    @YbTokenRetrofit
    Retrofit tokenRetrofit();

    TokenServer tokenServer();

    void inject(YbTokenObersver ybTokenObersver);

}
