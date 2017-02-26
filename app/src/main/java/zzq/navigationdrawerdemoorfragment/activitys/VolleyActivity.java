package zzq.navigationdrawerdemoorfragment.activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import zzq.navigationdrawerdemoorfragment.R;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, get, post, imagebtn, imageLoaderBtn, netimagebtn;
    private ImageView imageView;
    private RequestQueue mQueue;
    private StringRequest stringRequest, stringRequest2;
    private JsonObjectRequest jsonObjectRequest;
    private ImageRequest imageRequest;
    private ImageLoader imageLoader;
    private ImageLoader.ImageListener imageListener;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        /**
         * volley用法3步
         * 1. 创建一个RequestQueue对象。
         * 2. 创建一个Request对象。
         * 3. 将Request对象添加到RequestQueue里面。
         */
        init();
        initControl();
        initListener();
    }

    private void init() {
        mQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });

        jsonObjectRequest = new JsonObjectRequest(
                "http://web.juhe.cn:8080/constellation/getAll?consName=%E7%8B%AE%E5%AD%90%E5%BA%A7&type=today&key=634aa0e328a14711b0f99578de8660b0",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG", jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });

        stringRequest2 = new StringRequest(Request.Method.POST, "http://v.juhe.cn/toutiao/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "985697cc5d75df9a48aa166e6c1dc4fa");
                map.put("type", "top");
                return map;
            }
        };

        imageRequest = new ImageRequest("http://00.imgmini.eastday.com/mobile/20160726/20160726073539_fbcfb68d923e3a436247055c47e0ca78_1_mwpl_05500201.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        Log.d("TAG", bitmap.toString());
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        /**
         * ImageLoader已经不是继承自Request的了，所以它的用法也和我们之前学到的内容有所不同，总结起来大致可以分为以下四步：
         * 1. 创建一个RequestQueue对象。
         * 2. 创建一个ImageLoader对象。
         * 3. 获取一个ImageListener对象。
         * 4. 调用ImageLoader的get()方法加载网络上的图片。
         */

        //创建一个ImageLoader对象。
        imageLoader = new ImageLoader(mQueue, new BitmapCache());

    }

    private void initControl() {
        btn1 = (Button) findViewById(R.id.btn1);
        get = (Button) findViewById(R.id.get);
        post = (Button) findViewById(R.id.post);
        imagebtn = (Button) findViewById(R.id.imagebtn);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageLoaderBtn = (Button) findViewById(R.id.imageLoader);
        netimagebtn = (Button) findViewById(R.id.netimagebtn);
        networkImageView = (NetworkImageView) findViewById(R.id.netimageview);
    }

    private void initListener() {
        btn1.setOnClickListener(this);
        get.setOnClickListener(this);
        post.setOnClickListener(this);
        imagebtn.setOnClickListener(this);
        imageLoaderBtn.setOnClickListener(this);
        netimagebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mQueue.add(stringRequest);
                break;
            case R.id.get:
                mQueue.add(jsonObjectRequest);
                break;
            case R.id.post:
                mQueue.add(stringRequest2);
                break;
            case R.id.imagebtn:
                mQueue.add(imageRequest);
                break;
            case R.id.imageLoader:
//                imageLoader.get("http://00.imgmini.eastday.com/mobile/20160726/20160726073539_fbcfb68d923e3a436247055c47e0ca78_1_mwpl_05500201.png",
//                        imageListener);

                //获取一个ImageListener对象。
                imageListener = ImageLoader.getImageListener(imageView, //加载图片显示的控件
                        R.drawable.zry, //图片加载过程中显示的图片
                        R.mipmap.ic_launcher);  //图片加载失败显示的图片

                imageLoader.get("http://00.imgmini.eastday.com/mobile/20160726/20160726073539_fbcfb68d923e3a436247055c47e0ca78_1_mwpl_05500201.png",
                        imageListener, 200, 200); //后两个参数为指定图片最大宽度和高度
                break;
            case R.id.netimagebtn:
                networkImageView.setDefaultImageResId(R.drawable.zry);
                networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
                networkImageView.setImageUrl("http://00.imgmini.eastday.com/mobile/20160726/20160726073539_fbcfb68d923e3a436247055c47e0ca78_1_mwpl_05500201.png",
                        imageLoader);
                break;
        }
    }

    //图片缓存
    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }

}
