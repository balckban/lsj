package com.idogs.laosiji.basic.http.covert;


import com.idogs.laosiji.basic.http.YbResponse;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by y on 2017/8/17.
 */

public class YbLocalResponseTransformer<V extends YbResponse<T>,T> implements SingleTransformer<Response<V>, T> {

    @Override
    public SingleSource<T> apply(@NonNull Single<Response<V>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .flatMap(new YbResponseFunc<>())
                .flatMap(new YbLocalResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
