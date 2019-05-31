package com.nopossiblebus.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.nopossiblebus.MyApplication;

public class ToastUtil {

    private static Toast toast = null; //toast

    /**
     * 自定义Toast,避免重复,界面中部弹出
     */
    public static void showCenterToast(Context context,Object msg) {
        String content;
        if (msg == null) {
            content = "null";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面中部弹出
     */
    public static void showCenterToast(Object msg) {
        String content;
        if (msg == null) {
            content = "null";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.app, content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showBottomToast(Context context,Object msg) {
        String content;
        if (msg == null) {
            content = "数据出错";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }
    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showBottomToast(Object msg) {
        String content;
        if (msg == null) {
            content = "数据出错";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.app, content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showBottomToast(Context context,int stringId) {
        String content;
        try {
            content =context.getString(stringId);
        } catch (Exception e) {
            content = String.valueOf(stringId);
        }

        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

}