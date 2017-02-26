package zzq.navigationdrawerdemoorfragment.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.RecyAndCardViewAdapter;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;
import zzq.navigationdrawerdemoorfragment.utils.StatusBarIntegrationUtils;

/**
 * Created by 志强 on 2016/7/21.
 */
public class RecyclerviewAndCardViewActivity extends Activity {

    private RecyclerView mRecyclerView;

    private RecyAndCardViewAdapter myAdapter;

    private List<Actor> actors = new ArrayList<Actor>();

    private String[] names = {"朱茵", "张柏芝", "张敏", "巩俐", "黄圣依", "赵薇", "莫文蔚", "如花"};

    private String[] pics = {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"};

    private ImageButton ib_goBackImageButton;//返回按钮
    private TextView tv_titleName;//标题文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarIntegrationUtils.getStatusBarIntegration(RecyclerviewAndCardViewActivity.this);
        setContentView(R.layout.recyclerviewandcardview_layout);
        initData();//初始化数据
        initComponent();//初始化组件
        setAdapter();//初始化适配器绑定数据


    }

    private void initComponent() {

        // 拿到RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        ib_goBackImageButton = (ImageButton) findViewById(R.id.ib_goBackImageButton);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);
        ib_goBackImageButton.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(RecyclerviewAndCardViewActivity.this));
        tv_titleName.setText("CardView加载本地图片");
        ib_goBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            actors.add(new Actor(names[i], pics[i]));
        }
    }

    private void setAdapter() {
        // 初始化自定义的适配器
        myAdapter = new RecyAndCardViewAdapter(this, actors);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 当点击actionbar上的添加按钮时，向adapter中添加一个新数据并通知刷新
            case R.id.action_add:
                if (myAdapter.getItemCount() != names.length) {
                    actors.add(new Actor(names[myAdapter.getItemCount()], pics[myAdapter.getItemCount()]));
                    mRecyclerView.scrollToPosition(myAdapter.getItemCount() - 1);
                    myAdapter.notifyDataSetChanged();
                }
                return true;
            // 当点击actionbar上的删除按钮时，向adapter中移除最后一个数据并通知刷新
            case R.id.action_remove:
                if (myAdapter.getItemCount() != 0) {
                    actors.remove(myAdapter.getItemCount() - 1);
                    mRecyclerView.scrollToPosition(myAdapter.getItemCount() - 1);
                    myAdapter.notifyDataSetChanged();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
