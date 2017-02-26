package zzq.navigationdrawerdemoorfragment.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.StaggeredGridLayoutAdapter;

/**
 * @说明:
 * @作者:张若愚
 * @时间:2016/7/21
 */
public class RecyclerViewFallActivity extends Activity implements View.OnClickListener {


    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredGridLayoutAdapter mAdapter;
    private Button add, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);

        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);

        initDatas();
        initListener();

        //1
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView_stagger);
        mAdapter = new StaggeredGridLayoutAdapter(this, mDatas);

        //2
        /**
         * RecyclerView.LayoutManager是一个抽象类，系统提供了3个实现类：
         *
         * LinearLayoutManager 线性管理器，支持横向、纵向。
         * GridLayoutManager 网格布局管理器
         * StaggeredGridLayoutManager 瀑布流式布局管理器
         */
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        //3
        mRecyclerView.setAdapter(mAdapter = new StaggeredGridLayoutAdapter(this, mDatas));

        //5
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();

    }

    /**
     * 模拟数据
     */
    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    /**
     * 设置监听
     */
    private void initListener() {
        add.setOnClickListener(this);
        delete.setOnClickListener(this);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.delete:
                mAdapter.removeData(1);
                break;
        }
    }

    /**
     * 处理点击事件
     */
    private void initEvent() {
        //给每个item设置点击事件，利用回调
        mAdapter.setOnItemClickListener(new StaggeredGridLayoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewFallActivity.this, "click++" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerViewFallActivity.this, "longClick++" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
