package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.identify.IdentifyActivity;
import com.nopossiblebus.entity.bean.SchemeListBean;

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
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.info_ll)
    LinearLayout infoLl;

    private Context mContext;


    private String status = "";
    private SchemeListBean listBean;

    public void setStatus(SchemeListBean bean) {
        listBean = bean;
        if (bean != null&&bean.getStatus()!=null){
            this.status = bean.getStatus();
        }
    }

    public IdentifyDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        mContext = context;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_identify);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        if (status.equals("")) {
            line1.setBackgroundColor(mContext.getResources().getColor(R.color.line_cc));
            line2.setBackgroundColor(mContext.getResources().getColor(R.color.line_cc));
            dialogTijiaoTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogTijiaoCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogShenheCir.setBackgroundResource(R.drawable.circle_point_x29_cc);
            dialogShenheTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_cc));
            dialogResultCir.setBackgroundResource(R.drawable.circle_point_x29_cc);
            dialogResultTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_cc));
            infoLl.setVisibility(View.GONE);
        } else if (status.equals("0")) {
            //审核中
            line1.setBackgroundColor(mContext.getResources().getColor(R.color.text_black_0f));
            line2.setBackgroundColor(mContext.getResources().getColor(R.color.line_cc));
            dialogTijiaoTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogTijiaoCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogShenheCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogShenheTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogResultCir.setBackgroundResource(R.drawable.circle_point_x29_cc);
            dialogResultTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_cc));
            dialogTijiao.setVisibility(View.GONE);
            reason.setVisibility(View.GONE);
            infoLl.setVisibility(View.VISIBLE);
            info.setText("资料正在审核中，请耐心等待...");
        } else {
            line1.setBackgroundColor(mContext.getResources().getColor(R.color.text_black_0f));
            line2.setBackgroundColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogTijiaoTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogTijiaoCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogShenheCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogShenheTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            dialogResultCir.setBackgroundResource(R.drawable.circle_point_x29);
            dialogResultTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_0f));
            if (status.equals("1")) {
                //通過
                dialogResultCir.setBackgroundResource(R.drawable.circle_point_x29_c99);
                dialogResultTxt.setTextColor(mContext.getResources().getColor(R.color.back_c99));
                dialogTijiao.setVisibility(View.GONE);
                reason.setVisibility(View.GONE);
                infoLl.setVisibility(View.VISIBLE);
                info.setText("资料审核通过，恭喜成为认证商家");
            } else if (status.equals("2")) {
                //失败
                dialogResultCir.setBackgroundResource(R.drawable.circle_point_x46_33);
                dialogResultTxt.setTextColor(mContext.getResources().getColor(R.color.text_black_33));
                dialogTijiao.setVisibility(View.VISIBLE);
                dialogTijiao.setText("重新提交");
                reason.setVisibility(View.VISIBLE);
                reason.setText("未通过原因："+listBean.getContacts());
                infoLl.setVisibility(View.VISIBLE);
                info.setText("资料审核未通过");
            }
        }
    }

    @OnClick(R.id.dialog_tijiao)
    public void onViewClicked() {
        IdentifyDialog.this.cancel();
        Intent intent = new Intent(mContext, IdentifyActivity.class);
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
