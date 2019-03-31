package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.identify.IdentifyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentifyDialog extends Dialog {


    @BindView(R.id.dialog_tijiao_cir)
    View dialogTijiaoCir;
    @BindView(R.id.dialog_shenhe_cir)
    View dialogShenheCir;
    @BindView(R.id.dialog_result_cir)
    View dialogResultCir;
    @BindView(R.id.dialog_tijiao_txt)
    TextView dialogTijiaoTxt;
    @BindView(R.id.dialog_shenhe_txt)
    TextView dialogShenheTxt;
    @BindView(R.id.dialog_result_txt)
    TextView dialogResultTxt;
    @BindView(R.id.dialog_tijiao)
    TextView dialogTijiao;

    private Context mContext;

    public IdentifyDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        mContext = context;
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_identify);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dialog_tijiao)
    public void onViewClicked() {
        IdentifyDialog.this.cancel();
        Intent intent = new Intent(mContext,IdentifyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.width = (int) getContext().getResources().getDimension(R.dimen.x850);
        attributes.gravity = Gravity.CENTER;
        getWindow().setAttributes(attributes);
    }
}
