package com.rjp.navigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Gimpo create on 2018/1/23 12:41
 * email : jimbo922@163.com
 */

public class NavChildView extends LinearLayout {
    private Context mContext;
    private ImageView navIcon;
    private TextView navTitle;
    private int titleSelectedColor;
    private int titleUnSelectedColor;

    public NavChildView(Context context) {
        this(context, null);
    }

    public NavChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_nav_child_view, this);
        navIcon = (ImageView) findViewById(R.id.nav_icon);
        navTitle = (TextView) findViewById(R.id.nav_title);
    }

    /**
     * 设置标题和icon的间距
     *
     * @param midSpace  距离
     */
    public void setMidSpace(float midSpace) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, (int) midSpace, 0, 0);
        navTitle.setLayoutParams(params);
    }

    public void setTitle(String title) {
        navTitle.setText(title);
    }

    public void setIcon(int iconResource) {
        navIcon.setImageResource(iconResource);
    }

    public void setTitleSelectedColor(int tabSelectedColor) {
        this.titleSelectedColor = tabSelectedColor;
    }

    public void setTitleUnSelectedColor(int tabUnSelectedColor) {
        this.titleUnSelectedColor = tabUnSelectedColor;
    }

    @Override
    public void setSelected(boolean selected) {
        navTitle.setTextColor(selected ? titleSelectedColor : titleUnSelectedColor);
        super.setSelected(selected);
    }

    public void setTitleTextSize(float tabTextSize) {
        navTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
    }

    public void setIconWidth(float tabIconWidth) {
        ViewGroup.LayoutParams params = navIcon.getLayoutParams();
        params.width = params.height = (int) tabIconWidth;
        navIcon.setLayoutParams(params);
    }
}
