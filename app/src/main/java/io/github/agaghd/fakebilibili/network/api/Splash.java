package io.github.agaghd.fakebilibili.network.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : wjy
 * time   : 2018/05/04
 * desc   :
 */

public interface Splash {
    @GET("x/v2/splash")
    Call<ResponseBody> getSplashInfo(@QueryMap Map<String, Object> paramas);
}

/**
 * {
 * "code": 0,
 * "data": [{
 * "id": 1246,
 * "type": 1,
 * "animate": 1,
 * "duration": 3,
 * "start_time": 1525363200,
 * "end_time": 1525449540,
 * "thumb": "http://i0.hdslb.com/bfs/archive/461758c110cabfc058c139ee9ad468925a446f2b.jpg",
 * "hash": "385116b633e5c3bd8a4e40645ab3e669",
 * "times": 5,
 * "skip": 1,
 * "uri": "https://www.bilibili.com/bangumi/play/ss24049"
 * }, {
 * "id": 205,
 * "type": 4,
 * "animate": 1,
 * "duration": 3,
 * "start_time": 1479972295,
 * "end_time": 1480490696,
 * "thumb": "http://i0.hdslb.com/bfs/archive/caa9071bd04dd44f6802558213dc6ddd1ffe8709.png",
 * "hash": "1de9413c1662624ea98c4b311c6f67c4",
 * "times": 1,
 * "skip": 0,
 * "uri": ""
 * }],
 * "message": "0",
 * "ttl": 1,
 * "ver": "76545310489797446082018275"
 * }
 */
