package com.idogs.laosiji.basic.http.covert;

/**
 * Created by y on 2017/8/16.
 */

import android.util.Log;

import com.idogs.laosiji.basic.http.YbResponse;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * <b>类名称：</b> LocalResponseFunc <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

class YbLocalResponseFunc<V extends YbResponse<T>,T> implements Function<V, Single<T>> {
    @Override
    public Single<T> apply(V tResponse) {
            return Single.just(tResponse.getResult());

    }
}