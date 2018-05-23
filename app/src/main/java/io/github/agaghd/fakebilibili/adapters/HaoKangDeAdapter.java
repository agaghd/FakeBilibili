package io.github.agaghd.fakebilibili.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.basemodel.utils.BaseRecyclerAdapter;
import io.github.agaghd.fakebilibili.R;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   :
 */

public class HaoKangDeAdapter extends BaseRecyclerAdapter<JSONObject> {

    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_haokangde, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, JSONObject data) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            if (data != null) {
                // TODO: 2018/5/23 使用实际数据显示
                String title = data.optString("title", "");
                holder.cardDescTv.setText(title);
                String cover = data.optString("cover", "");
                Glide.with(mContext).load(cover).centerCrop().into(holder.cardIv);
                String play = data.optString("play", "");
                holder.playTimesTv.setText(play);
                String reply = data.optString("reply", "");
                holder.commentsTv.setText(reply);
                String duration = data.optString("duration", "");
                holder.lengthTv.setText(duration);
                String tname = data.optString("tname");
                holder.cardTagTv.setText(tname);
            }
        }
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
