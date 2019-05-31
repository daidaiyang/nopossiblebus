package com.nopossiblebus.entity;

import com.nopossiblebus.entity.bean.SaveOrderBean;
import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.entity.bean.WeChatPayBean;
import com.nopossiblebus.http.Api.BaseResultEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpPostService {

    //登陆
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/user-login")
    Observable<BaseResultEntity<UserLoginData>> userLogin(@Body RequestBody body);

    //注册
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/user-enroll")
    Observable<BaseResultEntity<String>> userEnroll(@Body RequestBody body);
    //获取短信验证码
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/send-msg")
    Observable<BaseResultEntity<String>> sendMsg(@Body RequestBody body);
    //验证短信验证码
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/security-code")
    Observable<BaseResultEntity<String>> SecurityCode(@Body RequestBody body);
    //忘记密码-修改密码
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/modify-user-pwd-phone")
    Observable<BaseResultEntity<String>> modifyPwdByPhone(@Body RequestBody body);

    //加入购物车
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("shop-car/add-to-shop-car")
    Observable<BaseResultEntity<String>> addToShopCar(@Body RequestBody body);

    //修改购物车产品数量
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("shop-car/modify-shop-car-product-num")
    Observable<BaseResultEntity<String>> modifyCartNum(@Body RequestBody body);
    //批量删除购物车产品
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("shop-car/delete-batch-shop-car-product")
    Observable<BaseResultEntity<String>> deleteCartProduct(@Body RequestBody body);

    ///v1/user/modify-default-location  设置默认地址
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/modify-default-location")
    Observable<BaseResultEntity<String>> modifyDefaultLocation(@Body RequestBody body);

    //新增/修改用户地址
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/modify-location")
    Observable<BaseResultEntity<String>> modifyLocation(@Body RequestBody body);

    //微信支付  /pay/wx/open/app-pay
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("pay/wx/open/app-pay")
    Observable<BaseResultEntity<WeChatPayBean>> payWx(@Body RequestBody body);

    //创建订单(从购物车选择产品创建订单)
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/create-shop-car-order")
    Observable<BaseResultEntity<SaveOrderBean>> createOrderByCart(@Body RequestBody body);


    //供货申请 /v1/provide-product/apply
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("provide-product/apply")
    Observable<BaseResultEntity<String>> provideProductApply(@Body RequestBody body);

    //接单抢单
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/take-order")
    Observable<BaseResultEntity<String>> takeOrder(@Body RequestBody body);

    //发货
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/deliver-order")
    Observable<BaseResultEntity<String>> deliverOrder(@Body RequestBody body);

    //收货
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/affirm-goods")
    Observable<BaseResultEntity<String>> affirmGood(@Body RequestBody body);

    //添加商家认证
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/save-merchant-auth")
    Observable<BaseResultEntity<String>> saveAuth(@Body RequestBody body);






}
