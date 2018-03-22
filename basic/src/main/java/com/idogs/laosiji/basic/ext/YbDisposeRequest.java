package com.idogs.laosiji.basic.ext;

import io.reactivex.disposables.Disposable;

/**
 * Created by y on 2017/8/22.
 */

public interface YbDisposeRequest {

    /**
     * 设置Dispose
     * @param tag
     * @param d
     */
    void setRequestTag(String tag,Disposable d);

    /**
     * 设置毁掉取消
     * @param tag
     * @return
     */
    boolean cancelRequest(String tag);
}
