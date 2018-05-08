package io.github.agaghd.basemodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.github.agaghd.basemodel.utils.SharePreferenceUtil;

/**
 * author   :   wjy
 * time     :   2018/5/4
 * desc     :   Activity基类
 */
public class BaseActivity extends FragmentActivity {

    protected Context mContext;
    protected SharePreferenceUtil mSharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (mSharePreferenceUtil == null) {
            mSharePreferenceUtil = new SharePreferenceUtil(mContext);
        }
    }

}
