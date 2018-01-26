package com.rjp.navigationview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author Gimpo create on 2018/1/23 12:30
 * email : jimbo922@163.com
 */

public class NavigationView extends LinearLayout implements View.OnClickListener {
    public static final int DEFAULT_INDEX = 0;

    private Context mContext;
    //导航view的属性
    private int tabSelectedColor;               //字体选中颜色
    private int tabUnSelectedColor;             //字体未选中颜色
    private float tabMidSpace;                  //标题和icon的间距
    private float tabPaddingTop;                //顶部padding
    private float tabPaddingBottom;             //底部padding
    private float tabTextSize;                  //文字大小
    private float tabIconWidth;                 //图标的宽度

    private int selectIndex = DEFAULT_INDEX;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private int containerId;

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        if (attrs != null) {
            TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.NavigationView);
            tabSelectedColor = array.getColor(R.styleable.NavigationView_tab_selectedColor, Color.GRAY);
            tabUnSelectedColor = array.getColor(R.styleable.NavigationView_tab_unSelectedColor, Color.GRAY);
            tabMidSpace = array.getDimension(R.styleable.NavigationView_tab_midSpace, dp2px(mContext, 5));
            tabPaddingTop = array.getDimension(R.styleable.NavigationView_tab_paddingTop, dp2px(mContext, 10));
            tabPaddingBottom = array.getDimension(R.styleable.NavigationView_tab_paddingBottom, dp2px(mContext, 10));
            tabTextSize = array.getDimension(R.styleable.NavigationView_tab_textSize, dp2px(mContext, 14));
            tabIconWidth = array.getDimension(R.styleable.NavigationView_tab_iconWidth, dp2px(mContext, 20));
        }
        setOrientation(HORIZONTAL);
    }

    /**
     * 设置tab数据  图标和标题
     *
     * @param tabModels 数据
     */
    public void setTabs(List<TabModel> tabModels) {
        int size = tabModels.size();
        size = size > 5 ? 5 : size; //最多接受5个tab
        for (int i = 0; i < size; i++) {
            TabModel tabModel = tabModels.get(i);
            NavChildView childView = new NavChildView(mContext);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            childView.setLayoutParams(params);
            childView.setTitleSelectedColor(tabSelectedColor);
            childView.setTitleUnSelectedColor(tabUnSelectedColor);
            childView.setTitleTextSize(tabTextSize);
            childView.setTitle(tabModel.getTitle());
            childView.setIcon(tabModel.getIconResource());
            childView.setIconWidth(tabIconWidth);
            childView.setMidSpace(tabMidSpace);
            childView.setPadding(0, (int) tabPaddingTop, 0, (int) tabPaddingBottom);
            childView.setTag(i);
            childView.setOnClickListener(this);
            childView.setSelected(i == selectIndex);
            addView(childView);
        }
        //默认显示第0个Fragment
        showFragment(selectIndex);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        int currentIndex = (Integer) v.getTag();
        if (currentIndex != selectIndex) {
            //切换tab
            NavChildView preNavView = (NavChildView) getChildAt(selectIndex);
            preNavView.setSelected(false);
            v.setSelected(true);
            showFragment(currentIndex);
        } else {
            //再次点击当前tab
        }
        selectIndex = currentIndex;
    }

    /**
     * 切换fragment
     *
     * @param currentIndex 索引
     */
    private void showFragment(int currentIndex) {
        if (fragments == null) {
            return;
        }
        FragmentTransaction trx = fragmentManager.beginTransaction();
        trx.hide(fragments.get(selectIndex));
        Fragment currentFragment = fragments.get(currentIndex);
        if (!currentFragment.isAdded()) {
            trx.add(containerId, currentFragment);
        }
        trx.show(currentFragment).commitAllowingStateLoss();
    }

    /**
     * 设置fragment的容器Id
     *
     * @param containerId id
     */
    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
}
