package io.github.agaghd.fakebilibili.network.apimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import io.github.agaghd.fakebilibili.network.BiliBiliApi;
import io.github.agaghd.fakebilibili.network.api.Splash;
import io.github.agaghd.fakebilibili.utils.Constants;
import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * author : wjy
 * time   : 2018/05/08
 * desc   :
 */

public class SplashImpl {

    public static void getSplashInfo(Callback<ResponseBody> callback) {
        Splash splash = BiliBiliApi.app.create(Splash.class);
        Map<String, Object> map = new LinkedHashMap<>();
        //https://app.bilibili.com/x/v2/splash?mobi_app=android&build=5250000&channel=xiaomi&width=1080&height=1920&ver=0
        map.put("mobi_app", "android");
        map.put("build", 5250000);
        map.put("channel", "xiaomi");
        map.put("width", Constants.DISPLAY_WIDTH);
        map.put("height", Constants.DISPLAY_HEIGHT);
        map.put("ver", 0);
        splash.getSplashInfo(map).enqueue(callback);
    }
}
