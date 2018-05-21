package io.github.agaghd.fakebilibili.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.agaghd.basemodel.utils.SharePreferenceUtil;
import io.github.agaghd.fakebilibili.BaseActivity;
import io.github.agaghd.fakebilibili.FakeBilibiliApplication;
import io.github.agaghd.fakebilibili.MainActivity;
import io.github.agaghd.fakebilibili.R;
import io.github.agaghd.fakebilibili.network.apimpl.SplashImpl;
import io.github.agaghd.fakebilibili.utils.Constants;
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

    private static final String NAME_SPLASH_DEFAULT = "splash_default";
    private static final String NAME_SPLASH_CM = "splash_cm";
    private static final String NAME_CM_URL = "cm_url";

    @Bind(R.id.welcome_iv)
    ImageView welcomeIv;
    @Bind(R.id.timer_btn)
    TextView timerBtn;

    private boolean isCm;
    private String cmUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        datainit();
        refreshSplashInfo();
    }

    private void datainit() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Constants.DISPLAY_WIDTH = displayMetrics.widthPixels;
        Constants.DISPLAY_HEIGHT = displayMetrics.heightPixels;
        Constants.TOUCH_SLOP = ViewConfiguration.get(mContext).getScaledTouchSlop();
        String splashDefault = mSharePreferenceUtil.getString(NAME_SPLASH_DEFAULT);
        cmUrl = mSharePreferenceUtil.getString(NAME_CM_URL);
        final TimeCountSplash timeCountSplash = new TimeCountSplash(4000, 1000);
        if (TextUtils.isEmpty(splashDefault)) {
            welcomeIv.setImageResource(R.drawable.welcome);
            timeCountSplash.start();
        } else {
            String splashCM = mSharePreferenceUtil.getString(NAME_SPLASH_CM);
            if (TextUtils.isEmpty(splashCM)) {
                timeCountSplash.start();
                Glide.with(mContext)
                        .load(splashDefault)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .override(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT)
                        .into(welcomeIv);
            } else {
                isCm = true;
                final SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        welcomeIv.setImageBitmap(resource);
                        timerBtn.setVisibility(View.VISIBLE);
                        timeCountSplash.start();
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        timeCountSplash.start();
                    }
                };
                Glide.with(mContext)
                        .load(splashCM)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .override(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT)
                        .into(simpleTarget);
            }
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private static void refreshSplashInfo() {
        SplashImpl.getSplashInfo(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        try {
                            JSONObject result = new JSONObject(body.string());
                            JSONArray data = result.optJSONArray("data");
                            if (data != null && data.length() > 0) {
                                boolean isAD = data.length() > 1;
                                JSONObject cmJSON = data.getJSONObject(0);
                                JSONObject splashJSON = data.getJSONObject(data.length() - 1);
                                final String splashCM = isAD ? cmJSON.optString("thumb", "") : "";
                                final String defaultSplash = splashJSON.optString("thumb", "");
                                String cmUrl = cmJSON.optString("uri", "");
                                SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(FakeBilibiliApplication.getAppContext());
                                sharePreferenceUtil.put(NAME_SPLASH_CM, splashCM);
                                sharePreferenceUtil.put(NAME_SPLASH_DEFAULT, defaultSplash);
                                sharePreferenceUtil.put(NAME_CM_URL, cmUrl);
                                //默认图片下载本地
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Glide.with(FakeBilibiliApplication.getAppContext())
                                                .load(splashCM)
                                                .downloadOnly(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT);
                                        Glide.with(FakeBilibiliApplication.getAppContext())
                                                .load(defaultSplash)
                                                .downloadOnly(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT);

                                    }
                                }).start();
                            }
                        } catch (Exception e) {
                            onFailure(call, e);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(FakeBilibiliApplication.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.welcome_iv, R.id.timer_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.welcome_iv:
                if (isCm) {
                    //如果是广告，跳转到广告页面
                    if (!TextUtils.isEmpty(cmUrl)) {
                        // TODO: 2018/5/8
                        Toast.makeText(mContext, cmUrl, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.timer_btn:
                gotoMainActivity();
                break;
        }
    }

    private class TimeCountSplash extends CountDownTimer {

        public TimeCountSplash(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {
            gotoMainActivity();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            SpannableStringBuilder ssb = new SpannableStringBuilder();
            SpannableString spannable = new SpannableString((millisUntilFinished / 1000) + "");
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.mainThemeColor));
            BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.TRANSPARENT);
            spannable.setSpan(backgroundColorSpan, 0, spannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(foregroundColorSpan, 0, spannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append("跳过 ").append(spannable);
            timerBtn.setText(ssb);
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
