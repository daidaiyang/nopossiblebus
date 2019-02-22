package com.nopossiblebus.activies.main.ingood.goods;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.customview.CircleImageView;
import com.nopossiblebus.customview.WaveView;
import com.nopossiblebus.dialog.RecognitionDialog;
import com.nopossiblebus.mvp.MVPBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GoodsFragment extends MVPBaseFragment<GoodsContract.View, GoodsPresenter> implements GoodsContract.View {


    @BindView(R.id.ingood_goods_txt1)
    TextView ingoodGoodsTxt1;
    @BindView(R.id.ingood_goods_txt2)
    TextView ingoodGoodsTxt2;
    @BindView(R.id.ingood_goods_low_icon)
    ImageView ingoodGoodsLowIcon;
    @BindView(R.id.ingood_goods_icon)
    WaveView ingoodGoodsIcon;
    @BindView(R.id.ingood_goods_circle)
    CircleImageView ingoodGoodsCircle;
    @BindView(R.id.ingood_goods_icon_img)
    ImageView ingoodGoodsIconImg;
    @BindView(R.id.first_function_scan_icon)
    ImageView firstFunctionScanIcon;
    @BindView(R.id.first_function_scan_txt)
    TextView firstFunctionScanTxt;
    @BindView(R.id.first_function_scan)
    LinearLayout firstFunctionScan;
    @BindView(R.id.first_function_order_icon)
    ImageView firstFunctionOrderIcon;
    @BindView(R.id.first_function_order_txt)
    TextView firstFunctionOrderTxt;
    @BindView(R.id.first_function_order)
    LinearLayout firstFunctionOrder;
    Unbinder unbinder;

    private Animation animation = null;
    private Animator animationJump = null;

    private long downTime;
    private RecognitionDialog dialog = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.image_scale);
        animationJump = AnimatorInflater.loadAnimator(getContext(), R.animator.heart_jump);
        animationJump.setTarget(ingoodGoodsCircle);
        ingoodGoodsIcon.setDuration(2000);
        ingoodGoodsIcon.setInitialRadius(getContext().getResources().getDimension(R.dimen.x432) / 2.0f);
        ingoodGoodsIcon.setMaxRadius(getContext().getResources().getDimension(R.dimen.x576) / 2.0f);
        ingoodGoodsIcon.setStyle(Paint.Style.FILL);
        ingoodGoodsIcon.setColor(Color.parseColor("#FEEE94"));
        ingoodGoodsIcon.setInterpolator(new LinearOutSlowInInterpolator());
        ingoodGoodsIcon.start();
        ingoodGoodsIconImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downTime = System.currentTimeMillis();
                        animation.setFillAfter(true);
                        ingoodGoodsIconImg.startAnimation(animation);
                        ingoodGoodsIcon.stopImmediately();
                        ingoodGoodsCircle.setVisibility(View.VISIBLE);
                        animationJump.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        ingoodGoodsCircle.setVisibility(View.GONE);
                        animationJump.cancel();
                        ingoodGoodsIconImg.clearAnimation();
                        ingoodGoodsIcon.start();
                        if (System.currentTimeMillis() - downTime > 500) {
                            showDialog();
                            new Handler()
                                    .postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
//                                            firstSearchView.setVisibility(View.GONE);
//                                            firstResultView.setVisibility(View.VISIBLE);
                                            dialog.cancel();
                                        }
                                    }, 3000);
                        }
                        break;
                }
                return true;
            }
        });
    }


    private void showDialog() {
        if (dialog == null) {
            dialog = new RecognitionDialog(getContext());
        }
        dialog.show();
        dialog.setCloseListener(new RecognitionDialog.CloseListener() {
            @Override
            public void close() {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
