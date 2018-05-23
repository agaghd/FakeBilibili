package io.github.agaghd.fakebilibili.mainpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.R;
import io.github.agaghd.fakebilibili.adapters.HaoKangDeAdapter;
import io.github.agaghd.fakebilibili.network.apimpl.HaoKangDeImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    HaoKangDeAdapter haoKangDeAdapter;

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
        haoKangDeAdapter = new HaoKangDeAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        haokangdeRv.setLayoutManager(layoutManager);
        haokangdeRv.setItemAnimator(new DefaultItemAnimator());
        haokangdeRv.setAdapter(haoKangDeAdapter);
        haoKangDeAdapter.setDatas(datas);
        loadData(true);
    }

    private void loadData(final boolean isRefresh) {
        HaoKangDeImpl.getHaoKangDe(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return;
                }
                try {
                    JSONObject result = new JSONObject(responseBody.string());
                    JSONArray data = result.optJSONArray("data");
                    if (data == null) {
                        return;
                    }
                    if (isRefresh) {
                        // TODO: 2018/5/23 设置banner数据
                        JSONObject bannerJson = data.optJSONObject(0);
                    }
                    int startIndex = isRefresh ? 1 : 0;
                    if (data.length() < startIndex) {
                        return;
                    }
                    List<JSONObject> list = new ArrayList<>();
                    for (int i = startIndex; i < data.length(); i++) {
                        JSONObject item = data.optJSONObject(i);
                        if (item != null) {
                            list.add(item);
                        }
                    }
                    if (isRefresh) {
                        haoKangDeAdapter.setDatas(list);
                    } else {
                        haoKangDeAdapter.addDatas(list);
                    }
                } catch (JSONException | IOException e) {
                    onFailure(call, e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, isRefresh);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
