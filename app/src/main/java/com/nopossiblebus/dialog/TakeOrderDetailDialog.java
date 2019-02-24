package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nopossiblebus.R;

public class TakeOrderDetailDialog extends Dialog{
    private Context mContext;
    private int type;//type :0-抢单详情   1-配送详情  2评价详情

    public TakeOrderDetailDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
