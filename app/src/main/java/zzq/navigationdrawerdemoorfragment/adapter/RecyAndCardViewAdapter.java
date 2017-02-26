package zzq.navigationdrawerdemoorfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.activitys.Actor;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;

/**
 * Created by 志强 on 2016/7/21.
 */
public class RecyAndCardViewAdapter extends RecyclerView.Adapter<RecyAndCardViewAdapter.ViewHolder> {

    private List<Actor> actors;

    public Context mContext;

    public RecyAndCardViewAdapter(Context context, List<Actor> actors) {
        this.mContext = context;
        this.actors = actors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mImageView.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));
        viewHolder.mTextView.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));
        // 给ViewHolder设置元素
        Actor p = actors.get(i);
        viewHolder.mTextView.setText(p.name);
        viewHolder.mImageView.setImageDrawable(mContext.getDrawable(p.getImageResourceId(mContext)));

    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return actors == null ? 0 : actors.size();
    }

    // 重写的自定义ViewHolder
    public class ViewHolder
            extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mImageView = (ImageView) v.findViewById(R.id.pic);
        }
    }
}
