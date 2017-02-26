package zzq.navigationdrawerdemoorfragment.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.RecyclerViewAdapter1;
import zzq.navigationdrawerdemoorfragment.adapter.RecyclerViewAdapter2;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;
import zzq.navigationdrawerdemoorfragment.utils.StatusBarIntegrationUtils;

/**
 * Created by 志强 on 2016/6/23.
 */
public class RecyclerViewActivity extends Activity {

    RecyclerView rc_recyclerView, rc_recyclerView2;
    RecyclerViewAdapter1 adapter;
    RecyclerViewAdapter2 adapter2;
    List<String> list = new ArrayList<String>();
    private ImageButton ib_goBackImageButton;//返回按钮
    private TextView tv_titleName;//标题文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarIntegrationUtils.getStatusBarIntegration(RecyclerViewActivity.this);
        setContentView(R.layout.recyclerview_layout);
        initData();
        initControl();
        setAdapter1();
        setAdapter2();
    }


    private void initData() {
    }

    private void initControl() {
        rc_recyclerView = (RecyclerView) findViewById(R.id.rc_recyclerView);
        rc_recyclerView2 = (RecyclerView) findViewById(R.id.rc_recyclerView2);
        ib_goBackImageButton = (ImageButton) findViewById(R.id.ib_goBackImageButton);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);
        ib_goBackImageButton.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(RecyclerViewActivity.this));
        tv_titleName.setText("初识Recyclerview,左右上下滑动");
        ib_goBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
    String[] dataset = new String[20];

    private void setAdapter1() {
        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        rc_recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rc_recyclerView.setHasFixedSize(true);
        // 创建数据集

        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "item" + i;
        }
        // 创建Adapter，并指定数据集
        adapter = new RecyclerViewAdapter1(dataset, isSelected);
        // 设置Adapter
        rc_recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewAdapter1.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_checkbox);//id
                if (checkBox.isChecked() == true) {

                    isSelected.put(position, false);
                    RecyclerViewAdapter1.setIsSelected(isSelected);
                    list.remove(data);
//                    checkBox.setChecked(false);
                } else {
                    isSelected.put(position, true);
                    RecyclerViewAdapter1.setIsSelected(isSelected);
                    list.add(data);
//                    checkBox.setChecked(true);
                }
                adapter.notifyDataSetChanged();
                setAdapter2();
            }
        });
        adapter.setCheckboxClickListener(new RecyclerViewAdapter1.ChenkBoxClickListtenter() {
                                             @Override
                                             public void onClick(View view, int position) {
                                                 if (isSelected.get(position)) {
                                                     isSelected.put(position, false);
                                                     RecyclerViewAdapter1.setIsSelected(isSelected);
                                                     list.remove(dataset[position]);
                                                 } else {
                                                     isSelected.put(position, true);
                                                     RecyclerViewAdapter1.setIsSelected(isSelected);
                                                     list.add(dataset[position]);
                                                 }
                                                 adapter.notifyDataSetChanged();
                                                 setAdapter2();
                                             }
                                         }
        );
    }


    private void setAdapter2() {

        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        rc_recyclerView2.setLayoutManager(linearLayoutManager2);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rc_recyclerView2.setHasFixedSize(true);
        // 创建数据集
        // 创建Adapter，并指定数据集
        adapter2 = new RecyclerViewAdapter2(list);
        // 设置Adapter
        rc_recyclerView2.setAdapter(adapter2);
        adapter2.setOnItemClickListener2(new RecyclerViewAdapter2.OnRecyclerViewItemClickListener2() {
            @Override
            public void onItemClick(View view, String data, int position) {
                for (int i = 0; i < dataset.length; i++) {
                    if ((data).equals(dataset[i])) {
                        isSelected.put(i, false);
                        RecyclerViewAdapter1.setIsSelected(isSelected);
                        list.remove(dataset[i]);
                    }
                }
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
    }
}
