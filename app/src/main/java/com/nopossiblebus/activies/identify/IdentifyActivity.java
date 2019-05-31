package com.nopossiblebus.activies.identify;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossiblebus.R;
import com.nopossiblebus.adapter.IdentifyGroupAdapter;
import com.nopossiblebus.dialog.PicSelecterDialog;
import com.nopossiblebus.entity.bean.GroupTypeBean;
import com.nopossiblebus.entity.bean.TypeBean;
import com.nopossiblebus.mvp.MVPBaseActivity;
import com.nopossiblebus.utils.GlideImageLoader_gallery;
import com.nopossiblebus.utils.ToastUtil;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class IdentifyActivity extends MVPBaseActivity<IdentifyContract.View, IdentifyPresenter> implements IdentifyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.identify_cancle)
    TextView cancle;
    @BindView(R.id.identify_upyingye_rl)
    RelativeLayout yingyeRl;
    @BindView(R.id.identify_upyingye_img)
    ImageView yingyeImg;
    @BindView(R.id.identify_ypfood_rl)
    RelativeLayout foodRl;
    @BindView(R.id.identify_ypfood_img)
    ImageView foodImg;
    @BindView(R.id.identify_name)
    EditText name;
    @BindView(R.id.identify_tel)
    EditText tel;
    @BindView(R.id.identify_merchname)
    EditText merchname;
    @BindView(R.id.identify_commit)
    TextView commit;
    @BindView(R.id.identify_upidcard_zheng_rl)
    RelativeLayout identifyUpidcardZhengRl;
    @BindView(R.id.identify_upidcard_img)
    ImageView identifyUpidcardImg;
    @BindView(R.id.identify_upidcard_fan_rl)
    RelativeLayout identifyUpidcardFanRl;
    @BindView(R.id.identify_idcard_fan_img)
    ImageView identifyIdcardFanImg;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Map<String, String> map;
    private GalleryConfig config = null;
    private List<String> path = new ArrayList<>();
    private PicSelecterDialog picDialog = null;

    private int photoType = 0;//0身份证正面  1身份证反面  2营业执照  3食品流通许可证

    private IdentifyGroupAdapter mAdapter;
    private List<GroupTypeBean> mList;

    private ProgressDialog progressDialog = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("商家认证");
        titleBack.setVisibility(View.GONE);
        map = new HashMap<>();
        config = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader_gallery())
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .multiSelect(false)
                .provider("com.nopossiblebus.fileprovider")   // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
        mList = new ArrayList<>();
        mAdapter = new IdentifyGroupAdapter(getContext(), mList);
        mAdapter.setOnItemclick(onItemClick);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        if (picDialog == null) {
            picDialog = new PicSelecterDialog(getContext());
            picDialog.setOnPicDialogClick(onPicClick);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getGroup();
    }


    @Override
    public void setGroupData(List<GroupTypeBean> groupList) {
        mList.clear();
        mList.addAll(groupList);
        mAdapter.notifyDataSetChanged();
    }

    private void showPic(String s) {
        switch (photoType) {
            case 0:
                map.put("id_card_1", s);
                Glide.with(this)
                        .load(s)
                        .into(identifyUpidcardImg);
                break;
            case 1:
                map.put("id_card_0", s);
                Glide.with(this)
                        .load(s)
                        .into(identifyIdcardFanImg);
                break;
            case 2:
                map.put("business_license", s);
                Glide.with(this)
                        .load(s)
                        .into(yingyeImg);
                break;
            case 3:
                map.put("circulate_license", s);
                Glide.with(this)
                        .load(s)
                        .into(foodImg);
                break;
        }
    }

    @Override
    public void saveAuthFinish() {
        ToastUtil.showBottomToast("资料提交成功，请耐心等待审核");
        progressDialog.cancel();
        this.finish();
    }

    private IdentifyGroupAdapter.OnItemclick onItemClick = new IdentifyGroupAdapter.OnItemclick() {
        @Override
        public void onItemClick(View view, int position) {
            GroupTypeBean groupTypeBean = mList.get(position);
            groupTypeBean.setChecked(!groupTypeBean.isChecked);
            mList.remove(position);
            mList.add(position,groupTypeBean);
            mAdapter.notifyDataSetChanged();
        }
    };

    private PicSelecterDialog.OnPicDialogClick onPicClick = new PicSelecterDialog.OnPicDialogClick() {
        @Override
        public void onFromCamera(View view) {
            config.getBuilder().isOpenCamera(true).build();
            GalleryPick.getInstance().setGalleryConfig(config).open(IdentifyActivity.this);
            picDialog.cancel();
        }

        @Override
        public void onFromPic(View view) {
            config.getBuilder().isOpenCamera(false).build();
            GalleryPick.getInstance().setGalleryConfig(config).open(IdentifyActivity.this);
            picDialog.cancel();
        }
    };

    @OnClick({R.id.identify_cancle, R.id.identify_upyingye_rl, R.id.identify_upyingye_img,
            R.id.identify_ypfood_rl, R.id.identify_ypfood_img, R.id.identify_commit,
            R.id.identify_upidcard_zheng_rl, R.id.identify_upidcard_img,
            R.id.identify_upidcard_fan_rl, R.id.identify_idcard_fan_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.identify_cancle:
                IdentifyActivity.this.finish();
                break;
            case R.id.identify_upyingye_img:
            case R.id.identify_upyingye_rl:
                photoType = 2;
                picDialog.show();
                break;
            case R.id.identify_ypfood_img:
            case R.id.identify_ypfood_rl:
                photoType = 3;
                picDialog.show();
                break;
            case R.id.identify_upidcard_img:
            case R.id.identify_upidcard_zheng_rl:
                photoType = 0;
                picDialog.show();
                break;
            case R.id.identify_idcard_fan_img:
            case R.id.identify_upidcard_fan_rl:
                photoType = 1;
                picDialog.show();
                break;

            case R.id.identify_commit:
                commitIdentify();
                break;
        }
    }

    private void commitIdentify() {

        String mreName = merchname.getText().toString();
        String perName = name.getText().toString();
        String phone = tel.getText().toString();
        List<String> shemList = new ArrayList<>();
        for (GroupTypeBean bean: mList
        ) {
            if (bean.isChecked){
                shemList.add(bean.getId());
            }
        }
        if (TextUtils.isEmpty(mreName) || TextUtils.isEmpty(perName) || TextUtils.isEmpty(phone)) {
            ToastUtil.showBottomToast("信息填写不能为空");
            return;
        }
        if (map.size() != 4) {
            ToastUtil.showBottomToast("请检查证件是否全部选择");
            return;
        }
        if (shemList.size()<1){
            ToastUtil.showBottomToast("请选择产品组合");
            return;
        }
        if (progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.show();
        mPresenter.commitIdenty(perName,mreName,phone,map,shemList);

    }



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
}
