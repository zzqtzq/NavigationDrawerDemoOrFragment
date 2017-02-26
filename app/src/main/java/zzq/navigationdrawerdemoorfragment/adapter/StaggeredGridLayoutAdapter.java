package zzq.navigationdrawerdemoorfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;

/**
 * @说明:
 * @作者:张若愚
 * @时间:2016/7/28
 */
public class StaggeredGridLayoutAdapter extends RecyclerView.Adapter<StaggeredGridLayoutAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<Integer> mHeights;
    private List<String> mDatas;

    /**
     * 监听接口
     */
    public interface OnItemClickListener {
        //两个抽象方法
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
        setmHeights();
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        setmHeights();
    }


    public StaggeredGridLayoutAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        setmHeights();
    }


    private void setmHeights() {
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_new, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //随机高度
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.tv.setLayoutParams(lp);
        holder.tv.setWidth(200);
        holder.tv.setText(mDatas.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;


        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.list_item);
        }
    }


}

