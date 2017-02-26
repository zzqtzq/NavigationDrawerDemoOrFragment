package zzq.navigationdrawerdemoorfragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import zzq.navigationdrawerdemoorfragment.R;

/**
 * Created by 志强 on 2016/6/23.
 */
public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.ViewHolder> implements View.OnClickListener {
    // 数据集
    private String[] mDataset;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelecteds;

    public RecyclerViewAdapter1(String[] dataset, HashMap<Integer, Boolean> isSelected) {
        super();
        this.mDataset = dataset;
        this.isSelecteds = isSelected;
        // 初始化数据
        initDate();
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    private ChenkBoxClickListtenter boxClickListenter = null;


    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    //checkbox interface
    public static interface ChenkBoxClickListtenter {
        void onClick(View view, int position);
    }


    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < mDataset.length; i++) {
            getIsSelected().put(i, false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelecteds;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        RecyclerViewAdapter1.isSelecteds = isSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(parent.getContext(), R.layout.item_checkbox, parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);

        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        holder.mTextView = (TextView) view.findViewById(R.id.tv_csname);
        holder.cb_checkbox = (CheckBox) view.findViewById(R.id.cb_checkbox);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据到ViewHolder上
        holder.mTextView.setText(mDataset[position]);
        holder.itemView.setTag(mDataset[position]);
        holder.itemView.setId(position);
        // 监听checkBox并根据原来的状态来设置新的状态

        holder.cb_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boxClickListenter.onClick(v, position);
            }
        });

//        holder.cb_checkbox.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                if (isSelecteds.get(position)) {
//                    isSelecteds.put(position, false);
//                    setIsSelected(isSelecteds);
//                } else {
//                    isSelecteds.put(position, true);
//                    setIsSelected(isSelecteds);
//                }
//
//            }
//        });

        // 根据isSelected来设置checkbox的选中状况
        holder.cb_checkbox.setChecked(getIsSelected().get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag(), v.getId());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setCheckboxClickListener(ChenkBoxClickListtenter listener) {
        this.boxClickListenter = listener;
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public CheckBox cb_checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
