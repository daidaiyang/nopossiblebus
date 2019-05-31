package com.nopossiblebus.utils;

import android.content.Context;

import java.text.DecimalFormat;

public class AppUtil {


    public static final String BASEIMGURL = "http://file.bukenengtech.cn/";

    // 微信APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wx6a6e70d91a1f8987";

    //安卓中像素px和dp的转换：
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static String getStatus(String status){
        int i = Integer.parseInt(status);
        switch (i){
            case 1:
                return "待付款";
            case 2:
                return "待退款";
            case 3:
                return "待补款";
            case 4:
                return "待接单";
            case 5:
                return "待配送";
            case 6:
                return "待发货";
            case 7:
                return "待收货";
            case 8:
                return "待评价";
            case 9:
                return "已完成";
        }
        return "";
    }
    public static String getApplyStatus(String status){
        int i = Integer.parseInt(status);
        switch (i){
            case 0:
                return "申请中";
            case 1:
                return "已通过";
            case 2:
                return "未通过";
                default:
                    return "申请中";
        }
    }

    /**
     * 获取两位小数
     *
     * @param d
     * @return
     */
    public static String get2xiaoshu(double d) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        String format = df.format(d);
        return format;
    }

    public static String get2xiaoshu(String s) {
        Double d = Double.parseDouble(s);
        DecimalFormat df = new DecimalFormat("#####0.00");
        String format = df.format(d);
        return format;
    }
}
