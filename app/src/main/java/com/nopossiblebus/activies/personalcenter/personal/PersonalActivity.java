package com.nopossiblebus.activies.personalcenter.personal;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypersonal);
    }
}
