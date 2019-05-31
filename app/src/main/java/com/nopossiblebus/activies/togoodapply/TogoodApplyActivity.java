package com.nopossiblebus.activies.togoodapply;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.activies.main.togood.applygood.ApplyRefresh;
import com.nopossiblebus.adapter.TogoodapplyAdapter;
import com.nopossiblebus.dialog.PicSelecterDialog;
import com.nopossiblebus.entity.bean.ApplyGoodReaultBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.GlideImageLoader_gallery;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TogoodApplyActivity extends MVPBaseActivity<TogoodApplyContract.View, TogoodApplyPresenter> implements TogoodApplyContract.View, View.OnClickListener {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.togoodapply_name)
    EditText applyName;
    @BindView(R.id.togoodapply_tel)
    EditText applyTel;
    @BindView(R.id.togoodapply_upidcard_zheng_rl)
    RelativeLayout upidcardZhengRl;
    @BindView(R.id.togoodapply_upidcard_img)
    ImageView upidcardImg;
    @BindView(R.id.togoodapply_upidcard_fan_rl)
    RelativeLayout upidcardFanRl;
    @BindView(R.id.togoodapply_idcard_fan_img)
    ImageView applyIdcardFanImg;
    @BindView(R.id.togoodapply_upyingye_rl)
    RelativeLayout upyingyeRl;
    @BindView(R.id.togoodapply_upyingye_img)
    ImageView upyingyeImg;
    @BindView(R.id.togoodapply_ypfood_rl)
    RelativeLayout ypfoodRl;
    @BindView(R.id.togoodapply_ypfood_img)
    ImageView ypfoodImg;
    @BindView(R.id.togoodapply_recyadd)
    TextView recyadd;
    @BindView(R.id.togoodapply_commit)
    TextView commit;
    @BindView(R.id.item_name)
    EditText itemName;
    @BindView(R.id.content_ll)
    LinearLayout contentLl;


    private List<ApplyGoodReaultBean> mData;
    private PicSelecterDialog picDialog = null;
    private int type = 0;
    private GalleryConfig config = null;
    private List<String> path = new ArrayList<>();
    private String idCardPath_z = "";
    private String idCardPath_f = "";
    private String yyzzPath = "";
    private String spltxkePath = "";
    private List<EditText> etList;
    private int position = 0;
    private boolean isClick = false;
    private int currentClickPosition = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x001) {
                mPresenter.getSearchData((String) msg.obj,etList.get(etList.size()-1));
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togoodapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("填写申请信息");
        if (picDialog == null) {
            picDialog = new PicSelecterDialog(getContext());
            picDialog.setOnPicDialogClick(onPicClick);
        }
        mData = new ArrayList<>();
        mData.add(new ApplyGoodReaultBean());
        config = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader_gallery())
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .multiSelect(false)
                .provider("com.nopossiblebus.fileprovider")   // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
        position =0;
        itemName.setTag(position);
        itemName.addTextChangedListener(onTextChange);
        itemName.setOnClickListener(this);
        etList = new ArrayList<>();
        etList.add(itemName);
    }



    @SuppressLint("CheckResult")
    private void selectPic(int type) {//type 1身份证正面  2身份证反面  3执照 4食品许可证
        this.type = type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            new RxPermissions(this)
                    .request(Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                if (picDialog == null) {
                                    picDialog = new PicSelecterDialog(getContext());
                                    picDialog.setOnPicDialogClick(onPicClick);
                                }
                                picDialog.show();
                            } else {
                                ToastUtil.showBottomToast("请允许权限后继续");
                            }
                        }
                    });
        } else {
            picDialog.show();
        }
    }

    private void addGoods() {
        if (!etList.get(position).getText().toString().equals("")){
            isClick = false;
            position++;
            EditText child = (EditText) LayoutInflater.from(getContext()).inflate(R.layout.item_togoodapply, contentLl, false);
            child.setTag(position);
            contentLl.addView(child,contentLl.getChildCount());
            contentLl.invalidate();
            child.requestFocus();
            child.setFocusable(true);
            child.setFocusableInTouchMode(true);
            child.addTextChangedListener(onTextChange);
            child.setOnClickListener(this);
            etList.add(child);
            currentClickPosition = position;
            mData.add(new ApplyGoodReaultBean());
        }

    }

    @Override
    public void setGoods(String name, String id) {
        ApplyGoodReaultBean bean = mData.get(currentClickPosition);
        bean.setName(name);
        bean.setId(id);
        EditText et = contentLl.findViewWithTag(currentClickPosition);
        et.setText(name);
    }
    private void commitApply() {
        if (mData.size()<=0){
            ToastUtil.showBottomToast("请输入商品");
            return;
        }else{
            for (int i=0;i<mData.size();i++){
                ApplyGoodReaultBean bean = mData.get(i);
                if (bean.getName()==null||bean.getName().equals("")||bean.getId()==null||bean.getName().equals("")){
                    ToastUtil.showBottomToast("产品必须要从列表中选择");
                    return;
                }
            }
        }
        String phone = applyTel.getText().toString();
        String name = applyName.getText().toString();
        if (phone.equals("")||name.equals("")){
            ToastUtil.showBottomToast("请填写负责人姓名和电话");
            return;
        }
        if (idCardPath_z.equals("")||idCardPath_f.equals("")||yyzzPath.equals("")||spltxkePath.equals("")){
            ToastUtil.showBottomToast("请选择相应的图片");
            return;
        }
        mPresenter.upPic(idCardPath_z, idCardPath_f, yyzzPath, spltxkePath, mData, name, phone);
    }

    @Override
    public void applyFinish() {
        ToastUtil.showBottomToast("申请成功");
        EventBus.getDefault().postSticky(new ApplyRefresh(true));
        this.finish();
    }

    private void showPic(String picPath) {
        switch (type) {
            case 1:
                idCardPath_z = picPath;
                Glide.with(this)
                        .load(picPath)
                        .into(upidcardImg);
                break;
            case 2:
                idCardPath_f = picPath;
                Glide.with(this)
                        .load(picPath)
                        .into(applyIdcardFanImg);
                break;
            case 3:
                yyzzPath = picPath;
                Glide.with(this)
                        .load(picPath)
                        .into(upyingyeImg);
                break;
            case 4:
                spltxkePath = picPath;
                Glide.with(this)
                        .load(picPath)
                        .into(ypfoodImg);
                break;
        }
    }

    private TextWatcher onTextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            ApplyGoodReaultBean bean = mData.get(currentClickPosition);
            if (!s.equals("")&&!s.toString().equals(bean.getName())){
                Message message = new Message();
                message.what = 0x001;
                message.obj = s.toString();
                handler.removeMessages(0x001);
                handler.sendMessageDelayed(message, 800);
            }
        }
    };

    @OnClick({R.id.title_back, R.id.togoodapply_upidcard_zheng_rl, R.id.togoodapply_upidcard_img,
            R.id.togoodapply_upidcard_fan_rl, R.id.togoodapply_idcard_fan_img,
            R.id.togoodapply_upyingye_rl, R.id.togoodapply_upyingye_img,
            R.id.togoodapply_ypfood_rl, R.id.togoodapply_ypfood_img, R.id.togoodapply_recyadd,
            R.id.togoodapply_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                TogoodApplyActivity.this.finish();
                break;
            case R.id.togoodapply_upidcard_img:
            case R.id.togoodapply_upidcard_zheng_rl:
                selectPic(1);
                break;

            case R.id.togoodapply_idcard_fan_img:
            case R.id.togoodapply_upidcard_fan_rl:
                selectPic(2);
                break;

            case R.id.togoodapply_upyingye_img:
            case R.id.togoodapply_upyingye_rl:
                selectPic(3);
                break;
            case R.id.togoodapply_ypfood_img:
            case R.id.togoodapply_ypfood_rl:
                selectPic(4);
                break;
            case R.id.togoodapply_recyadd:
                addGoods();
                break;
            case R.id.togoodapply_commit:
                commitApply();
                break;
        }
    }



    private PicSelecterDialog.OnPicDialogClick onPicClick = new PicSelecterDialog.OnPicDialogClick() {
        @Override
        public void onFromCamera(View view) {
            config.getBuilder().isOpenCamera(true).build();
            GalleryPick.getInstance().setGalleryConfig(config).open(TogoodApplyActivity.this);
            picDialog.cancel();
        }

        @Override
        public void onFromPic(View view) {
            config.getBuilder().isOpenCamera(false).build();
            GalleryPick.getInstance().setGalleryConfig(config).open(TogoodApplyActivity.this);
            picDialog.cancel();
        }
    };

    private IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
        }

        @Override
        public void onSuccess(List<String> photoList) {
            showPic(photoList.get(0));
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onError() {
        }
    };

    @Override
    public void onClick(View v) {
        isClick = true;
        currentClickPosition = (int) v.getTag();
    }
}
