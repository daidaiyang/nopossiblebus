package com.nopossiblebus.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
