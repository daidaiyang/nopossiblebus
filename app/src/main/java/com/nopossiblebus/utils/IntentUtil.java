package com.nopossiblebus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nopossiblebus.R;
import com.nopossiblebus.mvp.MVPBaseActivity;

public class IntentUtil {

    public IntentUtil() {
    }

    public static void startActivity(Context context,Class c){
        Intent intent = new Intent(context,c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class c){
        Intent intent = new Intent(activity,c);
        activity.startActivity(intent);
    }

    public static void startActivity(Context context,Class c,Bundle bundle){
        Intent intent = new Intent(context,c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void startActivity(Activity activity, Class c, Bundle bundle){
        Intent intent = new Intent(activity,c);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }


    public static void startActivityFor(Activity activity, Class c, int requestCode){
        Intent intent = new Intent(activity,c);
        activity.startActivityForResult(intent,requestCode);
        activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    public static void startActivityForResult(MVPBaseActivity activity, Class c, int requestCode){
        Intent intent = new Intent(activity,c);
        activity.startActivityForResult(intent,requestCode);
        activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    public static void startActivityForResult(MVPBaseActivity activity,Class c,int requestCode,Bundle bundle){
        Intent intent = new Intent(activity,c);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,requestCode);
        activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }




}
