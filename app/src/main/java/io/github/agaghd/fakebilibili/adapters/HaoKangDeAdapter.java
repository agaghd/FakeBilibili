package io.github.agaghd.fakebilibili.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 * desc   : 推荐页面RecyclerView的适配器
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
            final MyViewHolder holder = (MyViewHolder) viewHolder;
            if (data != null) {
                String title = data.optString("title", "");
                holder.cardTitleTv.setText(title);
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
                String desc = data.optString("desc");
                holder.cardDescTv.setText(desc);
                if (TextUtils.isEmpty(tname)) {
                    holder.lengthTv.setVisibility(View.INVISIBLE);
                    holder.cardAdTv.setVisibility(View.VISIBLE);
                    holder.cardDescTv.setVisibility(View.VISIBLE);
                } else {
                    holder.lengthTv.setVisibility(View.VISIBLE);
                    holder.cardAdTv.setVisibility(View.INVISIBLE);
                    holder.cardDescTv.setVisibility(View.INVISIBLE);
                }
            }
            holder.moreIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showIndifferentPopWindow(holder.moreIv);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showIndifferentPopWindow(holder.moreIv);
                    return true;
                }
            });
            holder.cardTagTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoTagActivity();
                }
            });
        }
    }

    private void showIndifferentPopWindow(View view) {
        // TODO: 2018/5/24 显示屏蔽内容窗口
        Toast.makeText(mContext, "显示屏蔽内容窗口", Toast.LENGTH_SHORT).show();
    }

    private void gotoTagActivity() {
        // TODO: 2018/5/24 前往Tag页面
        Toast.makeText(mContext, "前往Tag页面", Toast.LENGTH_SHORT).show();
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
        @Bind(R.id.card_title_tv)
        TextView cardTitleTv;
        @Bind(R.id.card_tag_tv)
        TextView cardTagTv;
        @Bind(R.id.card_ad_tv)
        TextView cardAdTv;
        @Bind(R.id.card_desc_tv)
        TextView cardDescTv;
        @Bind(R.id.more_iv)
        ImageView moreIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
