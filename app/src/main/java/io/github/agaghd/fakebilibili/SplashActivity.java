package io.github.agaghd.fakebilibili;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.basemodel.BaseActivity;

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
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
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
        Glide.with(mContext).load("").asBitmap().into(simpleTarget);
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
