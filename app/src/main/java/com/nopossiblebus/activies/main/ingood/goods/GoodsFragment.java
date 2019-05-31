package com.nopossiblebus.activies.main.ingood.goods;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.sunflower.FlowerCollector;
import com.nopossiblebus.R;
import com.nopossiblebus.activies.onekeysaveorder.OnekeysaveorderActivity;
import com.nopossiblebus.activies.search.SearchActivity;
import com.nopossiblebus.customview.CircleImageView;
import com.nopossiblebus.customview.WaveView;
import com.nopossiblebus.dialog.RecognitionDialog;
import com.nopossiblebus.mvp.MVPBaseFragment;
import com.nopossiblebus.utils.IntentUtil;
import com.nopossiblebus.utils.JsonParser;
import com.nopossiblebus.utils.LogUtil;
import com.nopossiblebus.utils.ToastUtil;
import com.nopossiblebus.zxing.android.CaptureActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;

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
    // 语音听写对象
    private SpeechRecognizer mIat;
    private static String TAG = GoodsFragment.class.getSimpleName();
    private StringBuffer buffer = new StringBuffer();
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private String resultType = "json";
    int ret = 0; // 函数调用返回值
    private String mResult = "";

    private static final int REQUEST_CODE_SCAN = 0x001;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingood_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);
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
        ingoodGoodsIconImg.setOnTouchListener(touchLis);
    }


    private void toSearch(String result) {
        Bundle bundle = new Bundle();
        bundle.putString("key",result);
        if (dialog!=null){
            dialog.cancel();
        }
        IntentUtil.startActivity(getContext(), SearchActivity.class,bundle);
    }



    /**
     * 录音按钮触摸事件
     */
    View.OnTouchListener touchLis = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 移动数据分析，收集开始听写事件
                        FlowerCollector.onEvent(getContext(), "iat_recognize");
                        buffer.setLength(0);
                        //清空内容
                        mResult = "";
                        mIatResults.clear();
                        //设置参数
                        mPresenter.setparam(mIat);
                        ret = mIat.startListening(mRecognizerListener);
                        if (ret != ErrorCode.SUCCESS) {
                            ToastUtil.showBottomToast("听写失败,错误码：" + ret);
                        } else {
                            ToastUtil.showBottomToast("请开始说话…");
                        }
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
                            mIat.stopListening();
                        }else {
                            mIat.cancel();
                        }
                        break;
                }
            return true;
        }
    };

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                ToastUtil.showBottomToast("初始化失败，错误码：" + code);
            }
        }
    };


    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
//            ToastUtil.showBottomToast("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            ToastUtil.showBottomToast(error.getPlainDescription(true));
            toSearch(mResult);
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            ToastUtil.showBottomToast("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if (resultType.equals("json")) {
                printResult(results);
            }
            dialog.cancel();
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            Log.d(TAG, "返回音频数据：" + data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };


    /**
     * 识别结果
     *
     * @param results
     */
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        mResult = resultBuffer.toString();
        toSearch(mResult);
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
                mIat.stopListening();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.first_function_scan, R.id.first_function_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_function_scan:
                startScan();
                break;
            case R.id.first_function_order:
                Intent intent = new Intent(getContext(), OnekeysaveorderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


    private void startScan(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            //6.0以上需要获取权限
            RxPermissions rxPermissions = new RxPermissions(getActivity());
            rxPermissions.request(Manifest.permission.CAMERA)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            Log.d("相机权限获取:",aBoolean+"");
                            if (aBoolean){
                                Intent intentScan = new Intent(getContext(),CaptureActivity.class);
                                intentScan.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivityForResult(intentScan,REQUEST_CODE_SCAN);
                            }else {
                                ToastUtil.showCenterToast(getContext(),"需要使用相机权限，请在设置中允许后继续");
                            }
                        }
                    });
        }else {
            Intent intentScan = new Intent(getContext(),CaptureActivity.class);
            intentScan.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intentScan,REQUEST_CODE_SCAN);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描二维码/条码回传
        if (requestCode ==REQUEST_CODE_SCAN&&resultCode ==RESULT_OK){
            if (data!=null){
                String scanContext = data.getStringExtra("codedContent");
                LogUtil.d("二维码扫描结果：",scanContext);
                ToastUtil.showCenterToast(getContext(),scanContext);
            }
        }
    }
}
