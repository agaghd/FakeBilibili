package io.github.agaghd.fakebilibili;

import android.app.Application;
import android.content.Context;

/**
 * author : wjy
 * time   : 2018/05/08
 * desc   :
 */

public class FakeBilibiliApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
