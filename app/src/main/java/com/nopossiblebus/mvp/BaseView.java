package com.nopossiblebus.mvp;

import android.content.Context;

import com.nopossiblebus.http.http.HttpManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     Context getContext();
     HttpManager getManager();
     RxAppCompatActivity getThis();
}
