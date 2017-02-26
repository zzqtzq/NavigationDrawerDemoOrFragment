package zzq.navigationdrawerdemoorfragment.activitys;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.ImageLoadgridAdapter;
import zzq.navigationdrawerdemoorfragment.base.BaseActivity;
import zzq.navigationdrawerdemoorfragment.utils.Constants;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;
import zzq.navigationdrawerdemoorfragment.utils.StatusBarIntegrationUtils;

/**
 * Created by 志强 on 2016/7/22.
 */

public class ImageLoaderActivity extends BaseActivity {

    private RecyclerView rlv_imageloaderView;
    private DisplayImageOptions options; // 设置图片显示相关参数
    private String[] imageUrls; // 图片路径
    private ImageLoadgridAdapter imageLoadgridAdapter;
    private List<String> list = new ArrayList<String>();
    private static final int GRID_ITEM_SIZE = 2;
    private ImageButton ib_goBackImageButton;//返回按钮
    private TextView tv_titleName;//标题文本

    @Override
    public int initResource() {
        StatusBarIntegrationUtils.getStatusBarIntegration(ImageLoaderActivity.this);
        return R.layout.layout_imageloader;
    }

    @Override
    public void initComponent() {
        rlv_imageloaderView = (RecyclerView) findViewById(R.id.rlv_imageloaderView);
        ib_goBackImageButton = (ImageButton) findViewById(R.id.ib_goBackImageButton);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);
        ib_goBackImageButton.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(ImageLoaderActivity.this));
        tv_titleName.setText("三级缓存加载网络图片");
        ib_goBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        //add name
        list.add("我跟你描述一个灵魂");
        list.add("它拥有不谢的青春");
        list.add("每当夜色降临");
        list.add("就会轻轻歌唱");
        list.add("让我在看你一遍");
        list.add("从南到北");
        list.add("像是北五环路蒙住的双眼");
        list.add("请你再讲一遍");
        list.add("关于那天");
        list.add("抱着盒子的姑娘");
        list.add("擦汗的男人");
        list.add("我知道那些夏天");
        list.add("就想青春一样回不来");
        list.add("代替梦想的也只是勉为其难");
        list.add("我知道吹过的牛逼");
        list.add("也会随青春一笑了之");
        list.add("让我困在城市里");
        list.add("纪念你");
        list.add("让我再尝一口");
        list.add("秋天的酒");
        setAdapter();
    }

    private void setAdapter() {
        //创建一个布局管理器

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_ITEM_SIZE);
        //设置布局管理器
        rlv_imageloaderView.setLayoutManager(gridLayoutManager);
        //如何item的height是固定的,设置这个属性可提高性能
        rlv_imageloaderView.setHasFixedSize(true);

        //设置显示图片参数
        imageUrls = Constants.images;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                // 设置图片下载期间显示的图片
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_error)
                // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)
                // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
                // 设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build();

//        Context mContext, String[] imageUrls, List<String> list,
////                ImageLoader imageLoader, DisplayImageOptions options
        //实例化适配器++
        imageLoadgridAdapter = new ImageLoadgridAdapter(ImageLoaderActivity.this, imageUrls, list, imageLoader, options);
        rlv_imageloaderView.setAdapter(imageLoadgridAdapter);
    }

    @Override
    public void addListener() {
        // RecyclerView CardView item 点击事件
        imageLoadgridAdapter.setCardViewOnItemClickListener(new ImageLoadgridAdapter.OnRecyclerViewCardViewItemClickListener() {
            @Override
            public void onCardViewItemClick(View view, String data, int position) {
                Toast.makeText(ImageLoaderActivity.this, "" + (position + 1) + "===" + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        imageLoadgridAdapter.setCardViewOnItemImageViewClickListener(new ImageLoadgridAdapter.OnRecyclerViewCardViewItemImageViewClickListener() {
            @Override
            public void onCardViewItemImageViewClick(View view, String data, int position) {
                Toast.makeText(ImageLoaderActivity.this, "点击图片:" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
