package io.github.agaghd.fakebilibili.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import io.github.agaghd.basemodel.utils.StringFormatUtil;
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
                String title = data.optString("title", "");
                holder.cardDescTv.setText(title);
                String cover = data.optString("cover", "");
                Glide.with(mContext).load(cover).centerCrop().into(holder.cardIv);
                String play = data.optString("play", "");
                play = StringFormatUtil.getFormatedNumsWithWan(play);
                holder.playTimesTv.setText(play);
                if (TextUtils.isEmpty(play)) {
                    holder.playTimesTv.setVisibility(View.INVISIBLE);
                } else {
                    holder.playTimesTv.setVisibility(View.VISIBLE);
                }
                String reply = data.optString("reply", "");
                reply = StringFormatUtil.getFormatedNumsWithWan(reply);
                holder.commentsTv.setText(reply);
                if (TextUtils.isEmpty(reply)) {
                    holder.commentsTv.setVisibility(View.INVISIBLE);
                } else {
                    holder.commentsTv.setVisibility(View.VISIBLE);
                }
                long duration = data.optLong("duration");
                holder.lengthTv.setText(StringFormatUtil.getHMSTimeString(duration));
                String tname = data.optString("tname");
                holder.cardTagTv.setText(tname);
                if (TextUtils.isEmpty(tname)) {
                    holder.lengthTv.setVisibility(View.INVISIBLE);
                } else {
                    holder.lengthTv.setVisibility(View.VISIBLE);
                }
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
