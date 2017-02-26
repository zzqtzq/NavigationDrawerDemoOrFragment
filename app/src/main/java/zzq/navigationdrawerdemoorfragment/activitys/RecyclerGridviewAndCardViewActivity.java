package zzq.navigationdrawerdemoorfragment.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.RecyGridViewAndCardViewAdapter;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;
import zzq.navigationdrawerdemoorfragment.utils.StatusBarIntegrationUtils;

/**
 * Created by 志强 on 2016/7/21.
 */
public class RecyclerGridviewAndCardViewActivity extends Activity {
    private RecyclerView rv_recylerViewTrainingProgram;
    private List<String> list = new ArrayList<String>();
    private RecyGridViewAndCardViewAdapter recyGridViewAdapter;
    private ImageButton ib_goBackImageButton;//返回按钮
    private TextView tv_titleName;//标题文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarIntegrationUtils.getStatusBarIntegration(RecyclerGridviewAndCardViewActivity.this);
        setContentView(R.layout.activity_trainingprogram);
        initData();
        initControl();
        setAdapter();
    }

    private void initData() {
        list.add("如今");
        list.add("我走在");
        list.add("人生十字路口");
        list.add("我知道");
        list.add("哪条路是对的");
        list.add("毫无例外");
        list.add("我就知道");
        list.add("但我从不走");
        list.add("为什么?");
        list.add("因为妈的太苦了");
    }

    private void initControl() {
        rv_recylerViewTrainingProgram = (RecyclerView) findViewById(R.id.rv_recylerViewTrainingProgram);
        ib_goBackImageButton = (ImageButton) findViewById(R.id.ib_goBackImageButton);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);
        ib_goBackImageButton.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(RecyclerGridviewAndCardViewActivity.this));
        tv_titleName.setText("CardView网格效果加载本地图片");
        ib_goBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setAdapter() {
//        //创建u一个线性布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        //设置布局管理器
//        rv_recylerViewTrainingProgram.setLayoutManager(linearLayoutManager);
//        //如果Item的高度是固定的设置属性提高性能
//        rv_recylerViewTrainingProgram.setHasFixedSize(true);
        //创建一个布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //滑动方向
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        //设置布局管理器
        rv_recylerViewTrainingProgram.setLayoutManager(gridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv_recylerViewTrainingProgram.setHasFixedSize(true);
        recyGridViewAdapter = new RecyGridViewAndCardViewAdapter(RecyclerGridviewAndCardViewActivity.this, list);
//        rv_recylerViewTrainingProgram.addItemDecoration(new MyItemDecoration());
        rv_recylerViewTrainingProgram.setAdapter(recyGridViewAdapter);
        recyGridViewAdapter.setOnItemClickListener(new RecyGridViewAndCardViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                Toast.makeText(RecyclerGridviewAndCardViewActivity.this, "" + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
//        recyGridViewAdapter.setOnItemClickListener(new RecyGridViewAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, String data, int position) {
//
//            }
//        });
    }
}
