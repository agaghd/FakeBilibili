package io.github.agaghd.fakebilibili;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import io.github.agaghd.basemodel.utils.SharePreferenceUtil;

/**
 * author   :   wjy
 * time     :   2018/5/4
 * desc     :   Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected SharePreferenceUtil mSharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (mSharePreferenceUtil == null) {
            mSharePreferenceUtil = new SharePreferenceUtil(mContext);
        }
        //使活动全屏
        if (Build.VERSION.SDK_INT >= 19) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }

}
