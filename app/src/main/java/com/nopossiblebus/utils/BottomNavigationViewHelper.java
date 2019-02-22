package com.nopossiblebus.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

public class BottomNavigationViewHelper {


    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        int count = view.getChildCount();
        if (count > 0) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            if (menuView != null) {
                menuView.setLabelVisibilityMode(1);
                menuView.updateMenuView();
            }
        }
    }
}
