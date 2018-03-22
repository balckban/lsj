package com.idogs.laosiji.basic.http;


/**
 * Created by y on 2017/7/19.
 */

public abstract class YbResponse<T> {

    /**
     * 获取响应中的结果
     * @return
     */
    public abstract T getResult();

}
