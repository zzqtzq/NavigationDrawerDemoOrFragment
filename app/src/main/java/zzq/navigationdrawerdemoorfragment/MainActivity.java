package zzq.navigationdrawerdemoorfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import zzq.navigationdrawerdemoorfragment.activitys.ImageLoaderActivity;
import zzq.navigationdrawerdemoorfragment.activitys.RecyclerGridviewAndCardViewActivity;
import zzq.navigationdrawerdemoorfragment.activitys.RecyclerViewActivity;
import zzq.navigationdrawerdemoorfragment.activitys.RecyclerViewFallActivity;
import zzq.navigationdrawerdemoorfragment.activitys.RecyclerviewAndCardViewActivity;
import zzq.navigationdrawerdemoorfragment.activitys.TrainingProgramActivity;
import zzq.navigationdrawerdemoorfragment.activitys.VolleyActivity;
import zzq.navigationdrawerdemoorfragment.helper.WaveHelper;
import zzq.navigationdrawerdemoorfragment.widget.WaveView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("zzq");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, null);
//        navigationView.addHeaderView(view);
        navigationView.setNavigationItemSelectedListener(this);
//        int statusBarHeight = getStatusBarHeight();
//        Toast.makeText(MainActivity.this, "" + statusBarHeight, Toast.LENGTH_SHORT).show();
        initView();
    }

    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private float aFloat = 0f;
    private float bFloat = 0.7f;
    private TextView tv_percentage;

    private void initView() {
        final WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);
        tv_percentage = (TextView) findViewById(R.id.tv_percentage);

        mWaveHelper = new WaveHelper(waveView, aFloat, bFloat);

        ((RadioGroup) findViewById(R.id.shapeChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.shapeCircle:
                                waveView.setShapeType(WaveView.ShapeType.CIRCLE);
                                break;
                            case R.id.shapeSquare:
                                waveView.setShapeType(WaveView.ShapeType.SQUARE);
                                break;
                            default:
                                break;
                        }
                    }
                });

        ((SeekBar) findViewById(R.id.seekBar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        mBorderWidth = i;
                        waveView.setBorder(mBorderWidth, mBorderColor);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorDefault),
                getResources().getColorStateList(android.R.color.white));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorRed),
                getResources().getColorStateList(R.color.red));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorGreen),
                getResources().getColorStateList(R.color.green));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorBlue),
                getResources().getColorStateList(R.color.blue));

        ((RadioGroup) findViewById(R.id.colorChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.colorRed:
                                waveView.setWaveColor(
                                        Color.parseColor("#28f16d7a"),
                                        Color.parseColor("#3cf16d7a"));
                                mBorderColor = Color.parseColor("#44f16d7a");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorGreen:
                                waveView.setWaveColor(
                                        Color.parseColor("#40b7d28d"),
                                        Color.parseColor("#80b7d28d"));
                                mBorderColor = Color.parseColor("#B0b7d28d");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorBlue:
                                waveView.setWaveColor(
                                        Color.parseColor("#88b8f1ed"),
                                        Color.parseColor("#b8f1ed"));
                                mBorderColor = Color.parseColor("#b8f1ed");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            default:
                                waveView.setWaveColor(
                                        WaveView.DEFAULT_BEHIND_WAVE_COLOR,
                                        WaveView.DEFAULT_FRONT_WAVE_COLOR);
                                mBorderColor = Color.parseColor("#44FFFFFF");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                        }
                    }
                });

        String percentage = "" + bFloat;
        percentage = percentage.substring(2);
        tv_percentage.setText(percentage + "0" + "%");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action RecyclerView
            startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
        } else if (id == R.id.nav_camera2) {
            startActivity(new Intent(MainActivity.this, TrainingProgramActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivity.this, RecyclerviewAndCardViewActivity.class));
        } else if (id == R.id.nav_gallery2) {
            startActivity(new Intent(MainActivity.this, RecyclerGridviewAndCardViewActivity.class));

        } else if (id == R.id.nav_slideshow) {
            //imageLoader
            startActivity(new Intent(MainActivity.this, ImageLoaderActivity.class));
        } else if (id == R.id.nav_slideshow2) {
            //volley intent
            startActivity(new Intent(MainActivity.this, VolleyActivity.class));
        } else if (id == R.id.nav_baiduMap) {
//            startActivity(new Intent(MainActivity.this, BaiduMapActivity.class));
        } else if (id == R.id.nav_baiduLoc) {
//            startActivity(new Intent(MainActivity.this, BaiduLocActivity.class));
        } else if (id == R.id.nav_falls) {
            startActivity(new Intent(MainActivity.this, RecyclerViewFallActivity.class));
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
