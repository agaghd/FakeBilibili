package io.github.agaghd.fakebilibili.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.agaghd.fakebilibili.R;

/**
 * author : wjy
 * time   : 2018/05/21
 * desc   : 抽屉的Item
 */

public class DrawerItemView extends LinearLayout {

    private ImageView iv;
    private TextView tv;

    private Drawable selectedDrawable;
    private Drawable unSelectedDrawable;
    private int selectedTextColor;
    private int unSelectedTextColor;

    public DrawerItemView(Context context) {
        this(context, null);
    }

    public DrawerItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DrawerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_drawer_item_view, this, true);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        int defaultSelectedTextColor = getResources().getColor(R.color.mainThemeColor);
        int defaultUnselectedTextColor = getResources().getColor(R.color.commonTextColor);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawerItemView, defStyleAttr, 0);
        selectedDrawable = ta.getDrawable(R.styleable.DrawerItemView_selectedDrawable);
        unSelectedDrawable = ta.getDrawable(R.styleable.DrawerItemView_unSelectedDrawable);
        selectedTextColor = ta.getColor(R.styleable.DrawerItemView_selectedTextColor, defaultSelectedTextColor);
        unSelectedTextColor = ta.getColor(R.styleable.DrawerItemView_unSelectedTextColor, defaultUnselectedTextColor);
        ta.recycle();
        iv.setImageDrawable(unSelectedDrawable);
        tv.setTextColor(unSelectedTextColor);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        Drawable drawable = selected ? selectedDrawable : unSelectedDrawable;
        int textColor = selected ? selectedTextColor : unSelectedTextColor;
        if (drawable != null) {
            iv.setImageDrawable(drawable);
        }
        tv.setTextColor(textColor);
    }
}
