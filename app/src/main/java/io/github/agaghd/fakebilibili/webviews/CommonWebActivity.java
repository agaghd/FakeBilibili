package io.github.agaghd.fakebilibili.webviews;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.agaghd.fakebilibili.BaseActivity;
import io.github.agaghd.fakebilibili.R;

/**
 * author : wjy
 * time   : 2018/05/25
 * desc   : 通用的展示Web用的Activity
 */

public class CommonWebActivity extends BaseActivity {

    @Bind(R.id.web_back_iv)
    ImageView webBackIv;
    @Bind(R.id.web_exit_iv)
    ImageView webExitIv;
    @Bind(R.id.web_title_tv)
    TextView webTitleTv;
    @Bind(R.id.web_share_iv)
    ImageView webShareIv;
    @Bind(R.id.web_more_iv)
    ImageView webMoreIv;
    @Bind(R.id.webView)
    WebView webView;

    String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        setColor(this, getResources().getColor(R.color.mainThemeColor));
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        uri = getIntent().getStringExtra("uri");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webTitleTv.setText(view.getTitle());
            }
        });
        if (!TextUtils.isEmpty(uri)) {
            webView.loadUrl(uri);
        }
    }

    @OnClick({R.id.web_back_iv, R.id.web_exit_iv, R.id.web_share_iv, R.id.web_more_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.web_back_iv: {
                onBackPressed();
                break;
            }
            case R.id.web_exit_iv: {
                finish();
                break;
            }
            case R.id.web_share_iv: {
                // TODO: 2018/5/25 分享
                Toast.makeText(mContext, "分享", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.web_more_iv: {
                // TODO: 2018/5/25 更多
                Toast.makeText(mContext, "更多", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
