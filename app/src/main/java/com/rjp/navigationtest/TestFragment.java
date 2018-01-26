package com.rjp.navigationtest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    private String sign;

    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment getInstance(String sign) {
        TestFragment mf = new TestFragment();
        mf.sign = sign;
        return mf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("------>", "onCreateView" + sign);
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv_sign = (TextView) view.findViewById(R.id.tv_sign);
        tv_sign.setText(sign + "...");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("------>", "onCreate"  + sign);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("------>", "onPause"  + sign);
    }
}
