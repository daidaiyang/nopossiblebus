package com.nopossiblebus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nopossiblebus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicSelecterDialog extends Dialog {

    @BindView(R.id.dialog_close)
    ImageView dialogClose;
    @BindView(R.id.dialog_fromCamera)
    LinearLayout dialogFromCamera;
    @BindView(R.id.dialog_fromPic)
    LinearLayout dialogFromPic;
    @BindView(R.id.title)
    TextView title;
    private Context mContext;

    private String titleName;

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    private OnPicDialogClick onPicDialogClick;

    public void setOnPicDialogClick(OnPicDialogClick onPicDialogClick) {
        this.onPicDialogClick = onPicDialogClick;
    }

    public PicSelecterDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        this.mContext = context;
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectpic);
        ButterKnife.bind(this);
        if (titleName!=null&&!titleName.equals("")){
            title.setText(titleName);
        }
    }


    @OnClick({R.id.dialog_close, R.id.dialog_fromCamera, R.id.dialog_fromPic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_close:
                PicSelecterDialog.this.cancel();
                break;
            case R.id.dialog_fromCamera:
                if (onPicDialogClick != null) {
                    onPicDialogClick.onFromCamera(view);
                }
                break;
            case R.id.dialog_fromPic:
                if (onPicDialogClick != null) {
                    onPicDialogClick.onFromPic(view);
                }
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
    }

    public interface OnPicDialogClick {
        void onFromCamera(View view);

        void onFromPic(View view);
    }
}
