package com.idogs.laosiji.basic.ext;

import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.core.base.BaseView;

/**
 * Created by y on 2017/8/16.
 */

public interface YbView extends BaseView,YbDisposeRequest {

    YbAppPreference getYbAppPreference();

    /**
     * 提示执行成功
     */
    void success(String message);

    /**
     * 提示失败
     */
    void error(String message);
}
