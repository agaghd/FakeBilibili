package io.github.agaghd.fakebilibili.mainpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.R;
import io.github.agaghd.fakebilibili.adapters.MyFragmentPageAdapter;

/**
 * author : wjy
 * time   : 2018/05/18
 * desc   : 主页面Fragment
 */

public class MainFragment extends Fragment {

    @Bind(R.id.main_tab)
    TabLayout mainTab;
    @Bind(R.id.main_view_pager)
    ViewPager mainViewPager;

    private List<Fragment> fragmentList;
    private MyFragmentPageAdapter myFragmentPageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        initData();
        return root;
    }

    private void initData() {
        mainViewPager.setOffscreenPageLimit(5);
        fragmentList = new ArrayList<>();
        Fragment haoKangDeFragment1 = new HaoKangDeFragment();
        Fragment haoKangDeFragment2 = new HaoKangDeFragment();
        Fragment haoKangDeFragment3 = new HaoKangDeFragment();
        fragmentList.add(haoKangDeFragment1);
        fragmentList.add(haoKangDeFragment2);
        fragmentList.add(haoKangDeFragment3);
        //设置page标题列表
        List<CharSequence> titleList = new ArrayList<>();
        for (int i = 0; i < mainTab.getTabCount(); i++) {
            TabLayout.Tab tab = mainTab.getTabAt(i);
            CharSequence title = tab != null ? tab.getText() : "";
            titleList.add(title);
        }
        myFragmentPageAdapter = new MyFragmentPageAdapter(getFragmentManager(), fragmentList, titleList);
        mainViewPager.setAdapter(myFragmentPageAdapter);
        mainTab.setupWithViewPager(mainViewPager);
        mainViewPager.setCurrentItem(1);
        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
