package com.nopossiblebus.entity;

import com.nopossiblebus.entity.bean.ApplyOrderDataBean;
import com.nopossiblebus.entity.bean.BasePageBean;
import com.nopossiblebus.entity.bean.MyAddressListBean;
import com.nopossiblebus.entity.bean.MyIntegralBean;
import com.nopossiblebus.entity.bean.MyKucunDataBean;
import com.nopossiblebus.entity.bean.OrderLineBean;
import com.nopossiblebus.entity.bean.OrderListBean;
import com.nopossiblebus.entity.bean.PrivinceBean;
import com.nopossiblebus.entity.bean.ProductGroupBean;
import com.nopossiblebus.entity.bean.ProductKindBean;
import com.nopossiblebus.entity.bean.ProductListBean;
import com.nopossiblebus.entity.bean.SchemeListBean;
import com.nopossiblebus.entity.bean.ShopCarProductBean;
import com.nopossiblebus.entity.bean.UserLoginData;
import com.nopossiblebus.http.Api.BaseResultEntity;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface HttpGetService {


    //获取用户信息
    @GET("user/user-detail")
    Observable<BaseResultEntity<UserLoginData>> getUserDetail();

    //产品列表
    @GET("product/product-list")
    Observable<BaseResultEntity<BasePageBean<ProductListBean>>> productList(@QueryMap Map<String,Object> map);

    //购物车列表
    @GET("shop-car/shop-car-list")
    Observable<BaseResultEntity<BasePageBean<ShopCarProductBean>>> shopcarList(@QueryMap Map<String,Object> map);

    //获取用户常用地址列表
    @GET("user/common-location-list")
    Observable<BaseResultEntity<List<MyAddressListBean>>> getAddressList();
    //删除地址
    @GET("user/delete-location")
    Observable<BaseResultEntity<String>> deleteLocation(@QueryMap Map<String,Object> map);

    //获取省市区树
    @GET("basic/city/city-tree")
    Observable<BaseResultEntity<List<PrivinceBean>>> getCityTree();

    //产品列表
    @GET("product/product-list")
    Observable<BaseResultEntity<BasePageBean<ProductListBean>>> getProductList(@QueryMap Map<String,Object> map);
    //订单列表
    @GET("order/order-list")
    Observable<BaseResultEntity<BasePageBean<OrderListBean>>> getOrderList(@QueryMap Map<String,Object> map);

    //产品类型列表
    @GET("basic/product-kind-list")
    Observable<BaseResultEntity<List<ProductKindBean>>> getProductKindList();

    //获取供货列表
    @GET("provide-product/list-apply")
    Observable<BaseResultEntity<BasePageBean<ApplyOrderDataBean>>> getApplyList(@QueryMap Map<String,Object> map);

    //商家配送订单列表
    @GET("order/delivery-order-list")
    Observable<BaseResultEntity<BasePageBean<OrderListBean>>> getDeliverOrderList(@QueryMap Map<String,Object> map);

    //积分列表
    @GET("integral/integral-list")
    Observable<BaseResultEntity<BasePageBean<MyIntegralBean>>> getIntegralList(@QueryMap Map<String,Object> map);
    //积分汇总
    @GET("integral/integral-sum")
    Observable<BaseResultEntity<String>> getIntegralSum();

    //产品库存
    @GET("user/find-product-store")
    Observable<BaseResultEntity<MyKucunDataBean>> getStore();


    //获取商家认证
    @GET("user/find-merchant-auth")
    Observable<BaseResultEntity<SchemeListBean>> getAuth();
    //获取产品组合
    @GET("user/find-product-scheme")
    Observable<BaseResultEntity<List<ProductGroupBean>>> getSchemeList();

}
