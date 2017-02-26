package zzq.navigationdrawerdemoorfragment.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

/**
 * Created by 志强 on 2016/7/21.
 */
public class ImageResourceUtil {
//    public static final String image[] = {R.drawable.p1, R.drawable.p2};

    public static Drawable createListSelectorDrawable(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
//            int[] attrs = new int[]{android.support.design.R.color.material_grey_50};
            TypedArray ta = context.obtainStyledAttributes(attrs);
            Drawable drawableFromTheme = ta.getDrawable(0);
            ta.recycle();
            return drawableFromTheme;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{}, new ColorDrawable(0x00000000));
            return stateListDrawable;
        }
    }

}
