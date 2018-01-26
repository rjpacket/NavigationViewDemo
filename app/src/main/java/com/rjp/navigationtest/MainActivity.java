package com.rjp.navigationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.rjp.navigationview.NavigationView;
import com.rjp.navigationview.TabModel;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ArrayList<TabModel> tabModels = new ArrayList<>();
        tabModels.add(new TabModel("首页", R.drawable.selector_home_button_1));
        tabModels.add(new TabModel("开奖", R.drawable.selector_home_button_2));
        tabModels.add(new TabModel("跟单", R.drawable.selector_home_button_3));
        tabModels.add(new TabModel("合买", R.drawable.selector_home_button_4));
        tabModels.add(new TabModel("我的", R.drawable.selector_home_button_5));
        navigationView.setFragmentManager(getSupportFragmentManager());
        navigationView.setContainerId(R.id.fragment_container);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TestFragment.getInstance("首页"));
        fragments.add(TestFragment.getInstance("开奖"));
        fragments.add(TestFragment.getInstance("跟单"));
        fragments.add(TestFragment.getInstance("合买"));
        fragments.add(TestFragment.getInstance("我的"));
        navigationView.setFragments(fragments);
        navigationView.setTabs(tabModels);
    }
}
