package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nopossiblebus.R;

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

    public IdentifyDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
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
