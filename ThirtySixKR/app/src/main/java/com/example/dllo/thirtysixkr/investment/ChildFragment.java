package com.example.dllo.thirtysixkr.investment;

import android.os.Bundle;

import com.example.dllo.thirtysixkr.BaseFragment;
import com.example.dllo.thirtysixkr.R;

public class ChildFragment extends BaseFragment{

    String url;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.url = String.valueOf(args);
    }

    @Override
    protected int setLayout() {
        return R.layout.child_fragment_investment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
