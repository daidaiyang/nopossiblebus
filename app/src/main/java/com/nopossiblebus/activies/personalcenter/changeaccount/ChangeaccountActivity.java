package com.nopossiblebus.activies.personalcenter.changeaccount;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeaccountActivity extends MVPBaseActivity<ChangeaccountContract.View, ChangeaccountPresenter> implements ChangeaccountContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeaccount);
    }
}
