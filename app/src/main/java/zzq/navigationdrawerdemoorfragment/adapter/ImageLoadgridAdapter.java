package zzq.navigationdrawerdemoorfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.utils.Bimp;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;

/**
 * Created by 志强 on 2016/7/22.
 */
public class ImageLoadgridAdapter extends RecyclerView.Adapter<ImageLoadgridAdapter.ViewHolder> {


    public Context mContext;
    private String[] imageUrls;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    private List<String> list;

    public ImageLoadgridAdapter(Context mContext, String[] imageUrls, List<String> list,
                                ImageLoader imageLoader, DisplayImageOptions options) {
        super();
        this.mContext = mContext;
        this.imageUrls = imageUrls;
        this.imageLoader = imageLoader;
        this.options = options;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageloadergridview_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_imageLoaderName.setText("" + (position + 1) + ":");
        if (imageUrls.length > 0) {

            imageLoader.displayImage(imageUrls[position],
                    holder.img_imageLoaderImageView, options);

            try {
                Bimp.bmp.add(Bimp.revitionImageSize(imageUrls[position]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        holder.itemView.setTag(list.get(position));
        holder.itemView.setId(position);
        holder.img_imageLoaderImageView.setTag(list.get(position));
        holder.img_imageLoaderImageView.setId(position);
    }

    @Override
    public int getItemCount() {
        return imageUrls == null ? 0 : imageUrls.length;
    }

    //Item点击事件
    private OnRecyclerViewCardViewItemClickListener mOnRecyclerViewCardViewItemClickListener = null;
    //Item里的ImageView点击事件
    private OnRecyclerViewCardViewItemImageViewClickListener mOnRecyclerViewCardViewItemImageViewClickListener;

    /**
     * 声明CardView item 点击事件
     */
    public static interface OnRecyclerViewCardViewItemClickListener {
        void onCardViewItemClick(View view, String data, int position);
    }

    /**
     * 声明CardView item里的imageView 点击事件
     */
    public static interface OnRecyclerViewCardViewItemImageViewClickListener {
        void onCardViewItemImageViewClick(View view, String data, int position);
    }

    public void setCardViewOnItemClickListener(OnRecyclerViewCardViewItemClickListener listener) {
        this.mOnRecyclerViewCardViewItemClickListener = listener;
    }

    public void setCardViewOnItemImageViewClickListener(OnRecyclerViewCardViewItemImageViewClickListener listener) {
        this.mOnRecyclerViewCardViewItemImageViewClickListener = listener;
    }


    // 重写的自定义ViewHolder
    public class ViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_imageLoaderName;
        public ImageView img_imageLoaderImageView;
        public RelativeLayout ll_rl_ImageLoaderCardViewRelativeLayout;

        public ViewHolder(View v) {
            super(v);
            //获取控件
            tv_imageLoaderName = (TextView) v.findViewById(R.id.tv_imageLoaderName);
            img_imageLoaderImageView = (ImageView) v.findViewById(R.id.img_imageLoaderImageView);
            ll_rl_ImageLoaderCardViewRelativeLayout = (RelativeLayout) v.findViewById(R.id.ll_rl_ImageLoaderCardViewRelativeLayout);
            //给item设置点击水波纹效果
            ll_rl_ImageLoaderCardViewRelativeLayout.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));
            v.setOnClickListener(this);
//            img_imageLoaderImageView.setOnClickListener(this);
            img_imageLoaderImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnRecyclerViewCardViewItemImageViewClickListener != null) {
                        mOnRecyclerViewCardViewItemImageViewClickListener.onCardViewItemImageViewClick(v, (String) v.getTag(), v.getId());
                    }
                }
            });
            img_imageLoaderImageView.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(mContext));

        }

        @Override
        public void onClick(View v) {
            if (mOnRecyclerViewCardViewItemClickListener != null) {
                //注意这里使用getTag方法获取数据
//                int position = getPosition();
                mOnRecyclerViewCardViewItemClickListener.onCardViewItemClick(v, (String) v.getTag(), v.getId());
            }
        }
    }
}
