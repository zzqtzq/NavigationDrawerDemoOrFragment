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
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;

/**
 * Created by 志强 on 2016/7/2.
 */
public class RecyGridViewAdapter extends RecyclerView.Adapter<RecyGridViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<String> list;
    private Context mContext;

    public RecyGridViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        view.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        holder.mTextView = (TextView) view.findViewById(R.id.tv_name);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(list.get(position));
        holder.itemView.setTag(list.get(position));
        holder.itemView.setId(position);

    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag(), v.getId());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
