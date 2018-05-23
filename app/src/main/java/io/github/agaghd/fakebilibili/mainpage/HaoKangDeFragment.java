package io.github.agaghd.fakebilibili.mainpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.R;
import io.github.agaghd.fakebilibili.adapters.HaoKangDeAdapter;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   : 推荐页面
 */

public class HaoKangDeFragment extends Fragment {

    @Bind(R.id.ranking_list_tv)
    TextView rankingListTv;
    @Bind(R.id.haokangde_tag_tv)
    TextView haokangdeTagTv;
    @Bind(R.id.haokangde_rv)
    RecyclerView haokangdeRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_haokangde, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        List<JSONObject> datas = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        datas.add(jsonObject);
        datas.add(jsonObject2);
        HaoKangDeAdapter haoKangDeAdapter = new HaoKangDeAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        haokangdeRv.setLayoutManager(layoutManager);
        haokangdeRv.setItemAnimator(new DefaultItemAnimator());
        haokangdeRv.setAdapter(haoKangDeAdapter);
        haoKangDeAdapter.setDatas(datas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
