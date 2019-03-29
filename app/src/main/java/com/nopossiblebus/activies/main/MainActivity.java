package com.nopossiblebus.activies.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblebus.R;
import com.nopossiblebus.activies.main.ingood.IngoodFragment;
import com.nopossiblebus.activies.main.takeorder.TakeorderFragment;
import com.nopossiblebus.activies.main.togood.TogoodFragment;
import com.nopossiblebus.activies.personalcenter.PersonalcenterActivity;
import com.nopossiblebus.activies.personalcenter.mymessage.MymessageActivity;
import com.nopossiblebus.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {


    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.main_mine)
    ImageView mainMine;
    @BindView(R.id.main_takeorder_check)
    TextView mainTakeorderCheck;
    @BindView(R.id.main_takeorder_line)
    View mainTakeorderLine;
    @BindView(R.id.main_takeorder)
    RelativeLayout mainTakeorder;
    @BindView(R.id.main_ingood_check)
    TextView mainIngoodCheck;
    @BindView(R.id.main_ingood_line)
    View mainIngoodLine;
    @BindView(R.id.main_ingood)
    RelativeLayout mainIngood;
    @BindView(R.id.main_togood_check)
    TextView mainTogoodCheck;
    @BindView(R.id.main_togood_line)
    View mainTogoodLine;
    @BindView(R.id.main_togood)
    RelativeLayout mainTogood;
    @BindView(R.id.main_message)
    FrameLayout mainMessage;
    @BindView(R.id.main_takeorder_titlell)
    LinearLayout mainTakeorderTitlell;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;

    private TakeorderFragment takeorderFragment;
    private IngoodFragment ingoodFragment;
    private TogoodFragment togoodFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        transaction = fragmentManager.beginTransaction();
        if (ingoodFragment == null) {
            ingoodFragment = new IngoodFragment();
        }
        transaction.add(R.id.main_frame, ingoodFragment);
        transaction.commit();
    }


    public void hideTitle(){
        mainTakeorderTitlell.setVisibility(View.GONE);
    }
    public void showTitle(){
        mainTakeorderTitlell.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.main_mine, R.id.main_takeorder, R.id.main_ingood, R.id.main_togood, R.id.main_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_mine:
                Intent intent = new Intent(this,PersonalcenterActivity.class);
                startActivity(intent);
                break;
            case R.id.main_takeorder:
                replaceFragment(0);
                changeLine(0);
                break;
            case R.id.main_ingood:
                replaceFragment(1);
                changeLine(1);
                break;
            case R.id.main_togood:
                replaceFragment(2);
                changeLine(2);
                break;
            case R.id.main_message:
                Intent intent1 = new Intent(this,MymessageActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void changeLine(int position) {
        hideAllLine();
        switch (position) {
            case 0:
                mainTakeorderLine.setVisibility(View.VISIBLE);
                TextPaint paint0 = mainTakeorderCheck.getPaint();
                paint0.setFakeBoldText(true);

                break;
            case 1:
                mainIngoodLine.setVisibility(View.VISIBLE);
                TextPaint paint1 = mainIngoodCheck.getPaint();
                paint1.setFakeBoldText(true);

                break;
            case 2:
                mainTogoodLine.setVisibility(View.VISIBLE);
                TextPaint paint2 = mainTogoodCheck.getPaint();
                paint2.setFakeBoldText(true);
                break;
        }
    }


    private void replaceFragment(int position) {
        transaction = fragmentManager.beginTransaction();
        hideAllFragment();
        switch (position) {
            case 0:
                if (takeorderFragment == null) {
                    takeorderFragment = new TakeorderFragment();
                    transaction.add(R.id.main_frame, takeorderFragment);
                } else {
                    transaction.show(takeorderFragment);
                }
                transaction.commit();
                break;
            case 1:
                if (ingoodFragment == null) {
                    ingoodFragment = new IngoodFragment();
                    transaction.add(R.id.main_frame, ingoodFragment);
                } else {
                    transaction.show(ingoodFragment);
                }
                transaction.commit();
                break;
            case 2:
                if (togoodFragment == null) {
                    togoodFragment = new TogoodFragment();
                    transaction.add(R.id.main_frame, togoodFragment);
                } else {
                    transaction.show(togoodFragment);
                }
                transaction.commit();
                break;
        }
    }

    private void hideAllFragment() {
        if (takeorderFragment != null) {
            transaction.hide(takeorderFragment);
        }
        if (ingoodFragment != null) {
            transaction.hide(ingoodFragment);
        }
        if (togoodFragment != null) {
            transaction.hide(togoodFragment);
        }
    }

    private void hideAllLine() {
        TextPaint paint0 = mainTakeorderCheck.getPaint();
        paint0.setFakeBoldText(false);
        TextPaint paint1 = mainIngoodCheck.getPaint();
        paint1.setFakeBoldText(false);
        TextPaint paint2 = mainTogoodCheck.getPaint();
        paint2.setFakeBoldText(false);
        mainIngoodLine.setVisibility(View.INVISIBLE);
        mainTakeorderLine.setVisibility(View.INVISIBLE);
        mainTogoodLine.setVisibility(View.INVISIBLE);
    }
}
