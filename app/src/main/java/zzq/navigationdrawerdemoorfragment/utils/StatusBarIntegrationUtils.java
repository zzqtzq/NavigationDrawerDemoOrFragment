package zzq.navigationdrawerdemoorfragment.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import zzq.navigationdrawerdemoorfragment.R;

/**
 * Created by 志强 on 2016/7/22.
 */
public class StatusBarIntegrationUtils {
    public static void getStatusBarIntegration(Activity mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true, mContext);
            SystemBarTintManager tintManager = new SystemBarTintManager(mContext);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    private static void setTranslucentStatus(boolean on, Activity activity) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
