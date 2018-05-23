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
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.R;

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
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        HaoKangDeAdapter haoKangDeAdapter = new HaoKangDeAdapter(jsonArray);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        haokangdeRv.setLayoutManager(layoutManager);
        haokangdeRv.setItemAnimator(new DefaultItemAnimator());
        haokangdeRv.setAdapter(haoKangDeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class HaoKangDeAdapter extends RecyclerView.Adapter<HaoKangDeAdapter.MyViewHolder> {

        private JSONArray data;

        public HaoKangDeAdapter(JSONArray data) {
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_haokangde, parent, false);
            return new MyViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (data == null) {
                return;
            }
            JSONObject jsonObject = data.optJSONObject(position);
            if (jsonObject == null) {
                return;
            }
            // TODO: 2018/5/23 使用实际数据显示
        }

        @Override
        public int getItemCount() {
            return data != null ? data.length() : 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.card_iv)
            ImageView cardIv;
            @Bind(R.id.play_times_tv)
            TextView playTimesTv;
            @Bind(R.id.comments_tv)
            TextView commentsTv;
            @Bind(R.id.length_tv)
            TextView lengthTv;
            @Bind(R.id.card_desc_tv)
            TextView cardDescTv;
            @Bind(R.id.card_tag_tv)
            TextView cardTagTv;
            @Bind(R.id.more_iv)
            ImageView moreIv;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

        }
    }


}
