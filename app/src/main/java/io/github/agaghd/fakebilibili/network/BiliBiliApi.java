package io.github.agaghd.fakebilibili.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * author : wjy
 * time   : 2018/05/04
 * desc   : B站API
 */

public class BiliBiliApi {

    public static final int TIME_OUT = 60 * 1000;

    public static final String APP_BILIBILI_COM = "https://app.bilibili.com/";
    public static final String API_BILIBILI_COM = "https://api.bilibili.com/";
    public static final String CLUB_BILIBILI_COM = "https://club.bilibili.com/";
    public static final String CM_BILIBILI_COM = "https://cm.bilibili.com/";
    public static final String DATA_BILIBILI_COM = "https://data.bilibili.com/";

    public static Retrofit app;
    public static Retrofit api;
    public static Retrofit club;
    public static Retrofit cm;
    public static Retrofit data;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OkHttpClient", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient baseClient = builder.build();
        app = new Retrofit.Builder()
                .baseUrl(APP_BILIBILI_COM)
                .client(baseClient)
                .build();
        api = new Retrofit.Builder()
                .baseUrl(APP_BILIBILI_COM)
                .client(baseClient)
                .build();
        club = new Retrofit.Builder()
                .baseUrl(APP_BILIBILI_COM)
                .client(baseClient)
                .build();
        cm = new Retrofit.Builder()
                .baseUrl(APP_BILIBILI_COM)
                .client(baseClient)
                .build();
        data = new Retrofit.Builder()
                .baseUrl(APP_BILIBILI_COM)
                .client(baseClient)
                .build();
    }

    private BiliBiliApi() {

    }

    public static void appRequest(Call<ResponseBody> call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
