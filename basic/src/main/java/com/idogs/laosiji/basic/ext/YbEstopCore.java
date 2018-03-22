package com.idogs.laosiji.basic.ext;

import android.app.Application;

import com.idogs.laosiji.basic.component.DaggerYbBasicComponent;
import com.idogs.laosiji.basic.component.DaggerYbNetworkComponent;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.basic.http.YbHttpModule;
import com.idogs.laosiji.basic.module.YbBasicModule;

/**
 * Created by y on 2017/8/15.
 */

public final class YbEstopCore {

    private static YbBasicComponent basicComponent;
    private static YbNetworkComponent networkComponent;

    private YbEstopCore() throws IllegalAccessException {
        throw new IllegalAccessException("can not new instance");
    }

    public static void init(Application application) {
        if (basicComponent == null) {
            basicComponent = DaggerYbBasicComponent
                    .builder()
                    .ybBasicModule(new YbBasicModule(application))
                    .build();
    }
        if (networkComponent == null) {
            networkComponent = DaggerYbNetworkComponent
                    .builder()
                    .ybBasicComponent(basicComponent)
                    .ybHttpModule(new YbHttpModule())
                    .build();
        }
    }

    public static YbBasicComponent getBasicComponent() {
        if (basicComponent == null) {
            throw new IllegalStateException("Yb Core need init at Application");
        }
        return basicComponent;
    }

    public static YbNetworkComponent getNetworkComponent() {
        if (networkComponent == null) {
            throw new IllegalStateException("Yb Core need init at Application");
        }
        return networkComponent;
    }
}
