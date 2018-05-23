package io.github.agaghd.fakebilibili.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   : 通用FragmentPagerAdapter
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<CharSequence> mPageTitleList;

    public MyFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<CharSequence> pageTitleList) {
        super(fm);
        mFragmentList = fragmentList;
        mPageTitleList = pageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList != null ? mFragmentList.get(position) : null;
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitleList != null && mPageTitleList.size() > position ? mPageTitleList.get(position) : "";
    }
}
