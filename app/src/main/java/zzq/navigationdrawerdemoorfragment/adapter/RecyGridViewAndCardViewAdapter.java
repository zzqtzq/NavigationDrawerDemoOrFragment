package zzq.navigationdrawerdemoorfragment.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;

/**
 * Created by 志强 on 2016/7/21.
 */
public class RecyGridViewAndCardViewAdapter extends RecyclerView.Adapter<RecyGridViewAndCardViewAdapter.ViewHolder> {

    private List<String> list;

    public Context mContext;

    public RecyGridViewAndCardViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }

    //android:background="@drawable/ripple_red"
    View v;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_card_view, viewGroup, false);
//        v.setBackgroundColor(Color.GRAY);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // 给ViewHolder设置元素
        viewHolder.mTextView.setText(list.get(i));
        viewHolder.mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.admin));
        viewHolder.itemView.setTag(list.get(i));
        viewHolder.itemView.setId(i);
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return list == null ? 0 : list.size();
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    // 重写的自定义ViewHolder
    public class ViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageView mImageView;
        public RelativeLayout ll_rl_CardViewRelativeLayout;
        public CardView cv_CardView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mImageView = (ImageView) v.findViewById(R.id.pic);
            ll_rl_CardViewRelativeLayout = (RelativeLayout) v.findViewById(R.id.ll_rl_CardViewRelativeLayout);
            ll_rl_CardViewRelativeLayout.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));
            v.setOnClickListener(this);
            cv_CardView = (CardView) v.findViewById(R.id.cv_CardView);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                int position = getPosition();
                mOnItemClickListener.onItemClick(v, (String) v.getTag(), position);
            }
        }
    }
}
