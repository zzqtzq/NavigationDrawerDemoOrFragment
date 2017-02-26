package zzq.navigationdrawerdemoorfragment.activitys;

import android.content.Context;

/**
 * Created by Dean Guo on 10/20/14.
 */
public class Actor {
    public String name;

    public String picName;

    public Actor(String name, String picName) {
        this.name = name;
        this.picName = picName;
    }

    public int getImageResourceId(Context context) {
        try {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
