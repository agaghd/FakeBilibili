package io.github.agaghd.fakebilibili.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.R;

/**
 * author : wjy
 * time   : 2018/05/24
 * desc   : bannerView
 */

public class BannerView extends FrameLayout {

    public static final int BANNER_SWITCH_TIME = 3000;

    @Bind(R.id.banner_pager)
    ViewPager bannerPager;
    @Bind(R.id.banner_ad_tv)
    TextView bannerAdTv;
    @Bind(R.id.banner_point_ll)
    LinearLayout bannerPointLl;

    private ImageView[] pointIvs;
    private ImageView[] bannerIvs;

    private BannerHandler bannerHandler;

    private int pointSize;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);
        pointSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
        ButterKnife.bind(this);
        bannerHandler = new BannerHandler(this);
    }

    public void setData(final JSONArray sourceData) {
        if (sourceData == null) {
            return;
        }
        if (sourceData.length() < 1) {
            return;
        }
        final int size = sourceData.length();
        bannerPointLl.removeAllViews();
        pointIvs = new ImageView[size];
        bannerIvs = new ImageView[size];
        for (int i = 0; i < size; i++) {
            pointIvs[i] = new ImageView(this.getContext());
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(pointSize, pointSize);
            pointParams.topMargin = pointSize / 2;
            pointParams.bottomMargin = pointSize / 2;
            pointParams.leftMargin = pointSize / 6;
            pointParams.rightMargin = pointSize / 6;
            pointIvs[i].setLayoutParams(pointParams);
            if (i == 0) {
                pointIvs[i].setImageResource(R.drawable.point_banner_selected);
            } else {
                pointIvs[i].setImageResource(R.drawable.point_banner_unselected);
            }
            bannerPointLl.addView(pointIvs[i]);
            bannerIvs[i] = new ImageView(this.getContext());
            ViewPager.LayoutParams bannerParams = new ViewPager.LayoutParams();
            bannerIvs[i].setLayoutParams(bannerParams);
            JSONObject jsonData = sourceData.optJSONObject(i);
            if (jsonData != null) {
                String image = jsonData.optString("image");
                Glide.with(this.getContext()).load(image).centerCrop().into(bannerIvs[i]);
                String uri = jsonData.optString("uri");
                bannerIvs[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2018/5/24 前往网页地址

                    }
                });
            }
        }
        bannerPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = bannerIvs[position % size];
                container.removeView(iv);
                container.addView(iv);
                return iv;
            }


        });
        bannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (ImageView iv : pointIvs) {
                    iv.setImageResource(R.drawable.point_banner_unselected);
                }
                pointIvs[position % size].setImageResource(R.drawable.point_banner_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bannerPager.setCurrentItem(size * 1000);
        bannerHandler.sendEmptyMessageDelayed(0, BANNER_SWITCH_TIME);
    }

    private static class BannerHandler extends Handler {

        private WeakReference<BannerView> weakReference;

        public BannerHandler(BannerView bannerView) {
            super();
            weakReference = new WeakReference<>(bannerView);
        }

        @Override
        public void handleMessage(Message msg) {
            BannerView bannerView = weakReference.get();
            if (bannerView != null) {
                int nextPosition = (bannerView.bannerPager.getCurrentItem() + 1);
                if (nextPosition == Integer.MAX_VALUE) {
                    nextPosition = Integer.MAX_VALUE % bannerView.bannerIvs.length;
                }
                bannerView.bannerPager.setCurrentItem(nextPosition);
            }
            sendEmptyMessageDelayed(0, BANNER_SWITCH_TIME);
        }
    }


}
