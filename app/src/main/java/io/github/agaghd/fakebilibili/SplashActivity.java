package io.github.agaghd.fakebilibili;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.basemodel.BaseActivity;
import io.github.agaghd.fakebilibili.network.BiliBiliApi;
import io.github.agaghd.fakebilibili.network.api.Splash;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author   :   wjy
 * time     :   2018/5/4
 * desc     :   欢迎界面
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.welcome_iv)
    ImageView welcomeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getSplashInfo();
    }

    private void getSplashInfo() {
        final SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                welcomeIv.setImageBitmap(resource);
                gotoMainActivity();
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                gotoMainActivity();
            }
        };
        Splash splash = BiliBiliApi.app.create(Splash.class);
        Map<String, Object> map = new LinkedHashMap<>();
        //https://app.bilibili.com/x/v2/splash?mobi_app=android&build=5250000&channel=xiaomi&width=1080&height=1920&ver=0
        map.put("mobi_app", "android");
        map.put("build", 5250000);
        map.put("channel", "xiaomi");
        map.put("width", 1080);
        map.put("height", 1920);
        map.put("ver", 0);
        splash.getSplashInfo(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    ResponseBody body = response.body();
                    if (body != null) {
                        final JSONObject result = new JSONObject(body.string());
                        JSONArray data = result.optJSONArray("data");
                        if (data != null && data.length() > 0) {
                            JSONObject data1 = data.getJSONObject(0);
                            if (data1 != null) {
                                final String thumb = data1.optString("thumb");
                                String uri = data1.optString("uri");
                                AsyncTask<Object, Object, File> asyncTask = new AsyncTask<Object, Object, File>() {
                                    @Override
                                    protected File doInBackground(Object... params) {
                                        FutureTarget<File> futureTarget = Glide.with(mContext.getApplicationContext()).load(thumb).downloadOnly(1080, 1920);
                                        File file;
                                        try {
                                            file = futureTarget.get();
                                        } catch (Exception e) {
                                            return null;
                                        }
                                        return file;
                                    }

                                    @Override
                                    protected void onPostExecute(File file) {
                                        super.onPostExecute(file);
                                        if (file != null) {
                                            Glide.with(mContext).load(thumb).asBitmap().into(simpleTarget);
                                        }
                                    }
                                };
                                asyncTask.execute();
                            }
                        }
                    }

                } catch (Exception e) {
                    onFailure(call, e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
