package io.github.agaghd.fakebilibili;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.homepage.HomeFragment;

/**
 * author : wjy
 * time   : 2018/05/25
 * desc   : 主页
 */

public class MainFragment extends Fragment {

    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.home_rb)
    RadioButton homeRb;
    @Bind(R.id.category_rb)
    RadioButton categoryRb;
    @Bind(R.id.following_rb)
    RadioButton followingRb;
    @Bind(R.id.mall_rb)
    RadioButton mallRb;

    private HomeFragment homeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setUpListeners();
        homeFragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        return view;
    }

    private void setUpListeners() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.home_rb: {
                        // TODO: 2018/5/25 首页
                        Toast.makeText(getActivity(), "首页", Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                        break;
                    }
                    case R.id.category_rb: {
                        // TODO: 2018/5/25 分区
                        Toast.makeText(getActivity(), "分区", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.following_rb: {
                        // TODO: 2018/5/25 动态
                        Toast.makeText(getActivity(), "动态", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.mall_rb: {
                        // TODO: 2018/5/25 会员购
                        Toast.makeText(getActivity(), "会员购", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
