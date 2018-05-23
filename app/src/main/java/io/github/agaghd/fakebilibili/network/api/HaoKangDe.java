package io.github.agaghd.fakebilibili.network.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   : 推荐内容获取接口
 * <p>
 * https://app.bilibili.com/x/feed/index?
 * ad_extra=0
 * &appkey=1d8b6e7d45233436
 * &build=5250000
 * &idx=1
 * &banner_hash=1
 * &login_event=0
 * &mobi_app=android
 * &network=wifi
 * &open_event=
 * &platform=android
 * &pull=false
 * &qn=16
 * &style=2
 * &ts=1525789011
 * &sign=d24a2baa1274bcbc3f614100e7f936ae
 */

public interface HaoKangDe {
    @GET("x/feed/index")
    Call<ResponseBody> getHaoKangDe(@QueryMap Map<String, Object> paramas);
}
