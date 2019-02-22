package com.nopossiblebus.dialog;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.nopossiblebus.R;
import com.nopossiblebus.customview.CircleImageView;


public class RecognitionDialog extends Dialog implements View.OnClickListener {

    private LinearLayout close;
    private CircleImageView circlr1;
    private CircleImageView circlr2;
    private CircleImageView circlr3;
    private CircleImageView circlr4;
    private CircleImageView circlr5;
    private CircleImageView circlr6;
    private CircleImageView circlr7;
    private CircleImageView circlr8;
    private Context mContext;

    private CloseListener closeListener;

    private Animator animator1;
    private Animator animator2;
    private Animator animator3;
    private Animator animator4;
    private Animator animator5;
    private Animator animator6;
    private Animator animator7;
    private Animator animator8;


    public RecognitionDialog(Context context) {
        super(context, R.style.LoadingDialog);
        this.mContext = context;
        setView();
    }

    private void setView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_recognition, null, false);
        close = view.findViewById(R.id.dialog_recognition_close);
        circlr1 = view.findViewById(R.id.dialog_recognition_circlr1);
        circlr2 = view.findViewById(R.id.dialog_recognition_circlr2);
        circlr3 = view.findViewById(R.id.dialog_recognition_circlr3);
        circlr4 = view.findViewById(R.id.dialog_recognition_circlr4);
        circlr5 = view.findViewById(R.id.dialog_recognition_circlr5);
        circlr6 = view.findViewById(R.id.dialog_recognition_circlr6);
        circlr7 = view.findViewById(R.id.dialog_recognition_circlr7);
        circlr8 = view.findViewById(R.id.dialog_recognition_circlr8);
        animator1 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator2 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator3 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator4 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator5 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator6 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator7 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator8 = AnimatorInflater.loadAnimator(mContext,R.animator.point_jump);
        animator1.setTarget(circlr1);
        animator3.setTarget(circlr3);
        animator5.setTarget(circlr5);
        animator7.setTarget(circlr7);
        animator2.setTarget(circlr2);
        animator4.setTarget(circlr4);
        animator6.setTarget(circlr6);
        animator8.setTarget(circlr8);
        close.setOnClickListener(this);
        animator2.setStartDelay(200);
        animator4.setStartDelay(200);
        animator6.setStartDelay(200);
        animator8.setStartDelay(200);
        animator1.start();
        animator2.start();
        animator3.start();
        animator4.start();
        animator5.start();
        animator6.start();
        animator7.start();
        animator8.start();
        this.setContentView(view);

    }

    @Override
    public void show() {
        super.show();

    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    @Override
    public void onClick(View v) {
        if (closeListener != null){
            closeListener.close();
        }
    }

    public interface CloseListener {
        void close();
    }
}
