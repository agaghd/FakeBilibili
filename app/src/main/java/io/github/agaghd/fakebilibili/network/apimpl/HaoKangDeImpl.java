package io.github.agaghd.fakebilibili.network.apimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import io.github.agaghd.fakebilibili.network.BiliBiliApi;
import io.github.agaghd.fakebilibili.network.api.HaoKangDe;
import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   :
 */

public class HaoKangDeImpl {

    public static void getHaoKangDe(Callback<ResponseBody> callback, boolean isRefresh) {
        HaoKangDe haoKangDe = BiliBiliApi.app.create(HaoKangDe.class);
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("ad_extra", 0);
        //不知道具体多少的时候有banner广告 10000肯定有了
        int build = isRefresh ? 10000 : 0;
        paramsMap.put("build", build);
        haoKangDe.getHaoKangDe(paramsMap).enqueue(callback);
    }

}
