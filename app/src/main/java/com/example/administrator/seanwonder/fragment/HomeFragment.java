package com.example.administrator.seanwonder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.seanwonder.R;
import com.example.administrator.seanwonder.common.BaseFragment;

/**
 * Created by sean on 2016/11/1.
 */

public class HomeFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        findView();
        initView();
        return view;
    }

    private void findView() {

    }

    private void initView() {

    }

}
