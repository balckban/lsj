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
 * <b>类名称：</b> ResponseTransformer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:25<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class YbResponseTransformer<V extends YbResponse<T>,T> implements SingleTransformer<Response<V>, V> {


    @Override
    public SingleSource<V> apply(@NonNull Single<Response<V>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .flatMap(new YbResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
