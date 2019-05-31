package com.nopossiblebus.activies.confirorder;

import android.app.ProgressDialog;
import android.content.Context;

import com.nopossiblebus.dialog.ConfirmPayDialog;
import com.nopossiblebus.entity.api.CreateOrderByCartApi;
import com.nopossiblebus.entity.api.PayWxApi;
import com.nopossiblebus.entity.bean.MyAddressListBean;
import com.nopossiblebus.entity.bean.SaveOrderBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.entity.bean.WeChatPayBean;
import com.nopossiblebus.http.listener.HttpOnNextListener;
import com.nopossiblebus.mvp.BasePresenterImpl;
import com.nopossiblebus.utils.AppUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ConfirorderPresenter extends BasePresenterImpl<ConfirorderContract.View> implements ConfirorderContract.Presenter{




    private IWXAPI iwxapi; //微信支付api
    private ProgressDialog pd = null;
    private ConfirmOrderBean confirmOrderBean = null;


    public void createOrder(MyAddressListBean bean, final List<ShopCarProductBean> mData, final String time){
        if (pd == null){
            pd = new ProgressDialog(mView.getContext());
        }
        pd.show();
        confirmOrderBean = new ConfirmOrderBean();
        confirmOrderBean.setProvince_no(bean.getProvince_no());
        confirmOrderBean.setProvince_name(bean.getProvince_name());
        confirmOrderBean.setCity_no(bean.getCity_no());
        confirmOrderBean.setCity_name(bean.getCity_name());
        confirmOrderBean.setDistrict_no(bean.getDistrict_no());
        confirmOrderBean.setDistrict_name(bean.getDistrict_name());
        confirmOrderBean.setAddress(bean.getAddress());
        confirmOrderBean.setTake_contacts(bean.getContacts());
        confirmOrderBean.setTake_tel_phone(bean.getPhone());
        confirmOrderBean.setDelivery_time(time);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<OrderLineBean> list = new ArrayList<>();
                for (int i=0;i<mData.size();i++){
                    ShopCarProductBean shopCarProductBean = mData.get(i);
                    OrderLineBean lineBean = new OrderLineBean();
                    lineBean.setProduct_id(shopCarProductBean.getProduct_id());
//                    lineBean.setProduct_id("a8iq3vglhjhq5c9yef71panso08di8gv8q4s7gww9y770tw6b5hs3yz3v6mngkkr");
                    lineBean.setNum(shopCarProductBean.getNum()+"");
                    list.add(lineBean);
                }
                confirmOrderBean.setOrder_line(list);
                mView.getThis().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CreateOrderByCartApi createOrderByCartApi = new CreateOrderByCartApi(create,mView.getThis());
                        createOrderByCartApi.initData(confirmOrderBean);
                        mView.getManager().doHttpDeal(createOrderByCartApi);
                    }
                });
            }
        }).start();

    }

    private HttpOnNextListener<SaveOrderBean> create = new HttpOnNextListener<SaveOrderBean>() {
        @Override
        public void onNext(SaveOrderBean s) {
            mView.saveOrderSuccess(s);
        }
    };


    public void payByWeChat(String money){
        PayWxApi payWxApi = new PayWxApi(payWechat,mView.getThis());
        payWxApi.initData(money);
        mView.getManager().doHttpDeal(payWxApi);
    }

    private HttpOnNextListener<WeChatPayBean> payWechat = new HttpOnNextListener<WeChatPayBean>() {
        @Override
        public void onNext(WeChatPayBean bean) {
            toWxPay(bean);
        }
    };


    private void toWxPay(final WeChatPayBean bean){
        iwxapi = WXAPIFactory.createWXAPI(mView.getContext(), null); //初始化微信api
        iwxapi.registerApp(AppUtil.APP_ID); //注册appid  appid可以在开发平台获取

        /**
         "appid": "wx6a6e70d91a1f8987",
         "noncestr": "1557733260823",
         "package": "Sign=WXPay",
         "partnerid": "1309161001",
         "prepayid": "wx131541009470694d77c36a0c3054525296",
         "sign": "185ED7CD2F666311D6E0982697516094",
         "timestamp": "1557733260"
         */
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = bean.getAppId();
                request.partnerId = bean.getMchId();
                request.prepayId =bean.getPrepayId();
                request.packageValue ="Sign=WXPay";
                request.nonceStr = bean.getNonceStr();
                request.timeStamp = bean.getTimeStamp();
                request.sign = bean.getPaySign();
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
