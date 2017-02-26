package zzq.navigationdrawerdemoorfragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;

/**
 * Created by 志强 on 2016/6/23.
 */
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> implements View.OnClickListener {


    List<String> list;

    public RecyclerViewAdapter2(List<String> list) {
        super();
        this.list = list;
    }

    private OnRecyclerViewItemClickListener2 mOnItemClickListener2 = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener2 {
        void onItemClick(View view, String data, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener2 != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener2.onItemClick(v, (String) v.getTag(), v.getId());
        }
    }


    public void setOnItemClickListener2(OnRecyclerViewItemClickListener2 listener2) {
        this.mOnItemClickListener2 = listener2;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox2, parent, false);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        //实例化textgv
        holder.mTextView = (TextView) view.findViewById(R.id.tv_csname2);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(list.get(position));
        holder.itemView.setId(position);
        //给textview控件赋值
        if (list.size() > 0) {
            holder.mTextView.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
