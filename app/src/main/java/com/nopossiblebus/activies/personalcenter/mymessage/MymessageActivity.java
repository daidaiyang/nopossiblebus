package com.nopossiblebus.activies.personalcenter.mymessage;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MymessageActivity extends MVPBaseActivity<MymessageContract.View, MymessagePresenter> implements MymessageContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
    }
}
