package zzq.navigationdrawerdemoorfragment.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zzq.navigationdrawerdemoorfragment.R;
import zzq.navigationdrawerdemoorfragment.adapter.RecyGridViewAdapter;
import zzq.navigationdrawerdemoorfragment.utils.ImageResourceUtil;
import zzq.navigationdrawerdemoorfragment.utils.MyItemDecoration;
import zzq.navigationdrawerdemoorfragment.utils.StatusBarIntegrationUtils;

/**
 * Created by 志强 on 2016/6/29.
 */
public class TrainingProgramActivity extends AppCompatActivity {

    private RecyclerView rv_recylerViewTrainingProgram;
    private List<String> list = new ArrayList<String>();
    private RecyGridViewAdapter recyGridViewAdapter;

    private ImageButton ib_goBackImageButton;//返回按钮
    private TextView tv_titleName;//标题文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarIntegrationUtils.getStatusBarIntegration(TrainingProgramActivity.this);
        setContentView(R.layout.activity_trainingprogram);
        initData();
        initControl();
        setAdapter();
    }

    private void initData() {
        list.add("1");
        list.add("2");
        list.add("3!");
        list.add("4?");
        list.add("5");
        list.add("6");
        list.add("7!");
        list.add("8?");
        list.add("9");
        list.add("10");
        list.add("11!");
        list.add("我能说啥?");
    }

    private void initControl() {
        rv_recylerViewTrainingProgram = (RecyclerView) findViewById(R.id.rv_recylerViewTrainingProgram);
        ib_goBackImageButton = (ImageButton) findViewById(R.id.ib_goBackImageButton);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);
        ib_goBackImageButton.setBackgroundDrawable(ImageResourceUtil.createListSelectorDrawable(TrainingProgramActivity.this));
        tv_titleName.setText("Recyclerview的网格效果");
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        //滑动方向
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        //设置布局管理器
        rv_recylerViewTrainingProgram.setLayoutManager(gridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv_recylerViewTrainingProgram.setHasFixedSize(true);
        recyGridViewAdapter = new RecyGridViewAdapter(TrainingProgramActivity.this, list);
        rv_recylerViewTrainingProgram.addItemDecoration(new MyItemDecoration());
        rv_recylerViewTrainingProgram.setAdapter(recyGridViewAdapter);
        recyGridViewAdapter.setOnItemClickListener(new RecyGridViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                Toast.makeText(TrainingProgramActivity.this, "" + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
