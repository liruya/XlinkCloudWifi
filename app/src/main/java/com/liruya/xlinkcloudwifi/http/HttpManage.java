package com.liruya.xlinkcloudwifi.http;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by liruya on 2017/1/17.
 */

public class HttpManage
{
    private static HttpManage instance;
    public static final MediaType JSON = MediaType.parse( "application/json; charset=utf-8" );

    private static final OkHttpClient mOkHttpClent = new OkHttpClient();

    public static String COMPANY_ID = "100fa2af5b048600";
    private final String host = "http://api2.xlink.cn";
    // url
    public final String registerUrl = host + "/v2/user_register";
    public final String loginUrl = host + "/v2/user_auth";
    public final String forgetUrl = host + "/v2/user/password/forgot";
    //.管理员（用户）获取所有设备分享请求列表
    public final String shareListUrl = host + "/v2/share/device/list";
    public final String getUserInfoUrl = host + "/v2/user/{user_id}";
    //获取某个用户绑定的设备列表。
    public final String subscribeListUrl = host + "/v2/user/%d/subscribe/devices";
    // public final String subscribeListUrl = host + "/v2/user/%d/subscribe/devices?version=%d";
    //设备管理员分享设备给指定用户
    public final String shareDeviceUrl = host + "/v2/share/device";
    //用户拒绝设备分享
    public final String denyShareUrl = host + "/v2/share/device/deny";
    //用户确认设备分享
    public final String acceptShareUrl = host + "/v2/share/device/accept";
    //获取设备信息
    public final String getDeviceUrl = host + "/v2/product/{product_id}/device/{device_id}";
    //订阅设备（待定）
    public final String subscribeUrl = host + "/v2/user/{user_id}/subscribe";
    //修改用户信息
    public final String modifyUserUrl = host + "/v2/user/{user_id}";
    //重置密码
    public final String resetPasswordUrl = host + "/v2/user/password/reset";
    //获取数据端点列表
    public final String getDatapointsUrl = host + "/v2/product/{product_id}/datapoints";
    //取消订阅设备
    public final String unsubscribeUrl = host + "/v2/user/{user_id}/unsubscribe";

    //.管理员或用户删除这条分享记录
    public final String deleteShareUrl = host + "/v2/share/device/delete/{invite_code}";

    //检查固件版本
    //    public final String checkUpdateUrl = host + "/v1/user/device/version";
    public final String checkUpdateUrl = "http://app.xlink.cn/v1/user/device/version";
    //固件升级
    //    public final String upgradeUrl = host + "/v1/user/device/version";
    public final String upgradeUrl = "http://app.xlink.cn/v1/user/device/upgrade";

    private HttpManage ()
    {
    }

    public static final HttpManage getInstance ()
    {
        return Holder.INSTANCE;
    }

    /**
     * http 邮箱注册接口
     *
     * @param mail 用户 邮箱
     * @param name 昵称（别名，仅供后台管理平台观看，对用户来说记住uid和pwd就行）
     * @param pwd  密码
     */
    public void registerUserByMail(String mail, String name, String pwd, Callback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("email", mail);
        params.put("nickname", name);
        params.put("corp_id", COMPANY_ID);
        params.put("password", pwd);
        params.put("source", "2");
        post(registerUrl, params, callback);
    }




    /**
     * 非异步线程
     * @param request
     * @return Response转换为String类型
     * @throws IOException
     */
    public static String execute ( Request request ) throws IOException
    {
        return mOkHttpClent.newCall(request).execute().body().string();
    }

    /**
     * post请求数据
     * @param url       地址
     * @param params    键值对
     * @param callback  回调方法
     */
    private void post( String url, Map<String, String> params, Callback callback )
    {
//        FormBody.Builder builder = new FormBody.Builder();
//        for ( String key : params.keySet() )
//        {
//            builder.add( key, params.get( key ) );
//        }
//        JSONObject object = new JSONObject( params );
        String json = new JSONObject(params).toString();
        RequestBody body = RequestBody.create( JSON, json );
        Request request = new Request.Builder().url( url ).post( body ).build();
        mOkHttpClent.newCall( request ).enqueue( callback );
    }

//    private void post( String url, String json, Callback callback )
    //    {
    //        RequestBody body = RequestBody.create( JSON, json );
    //        Request request = new Request.Builder().url( url ).post( body ).build();
    //        mOkHttpClent.newCall( request ).enqueue( callback );
    //    }

    /**
     * get请求数据
     * @param url
     * @param callback
     */
    private void get( String url, Callback callback )
    {
        Request request = new Request.Builder().url( url ).build();
        mOkHttpClent.newCall( request ).enqueue( callback );
    }

    private void put( String url, Map<String, String> params, Callback callback )
    {
        FormBody.Builder builder = new FormBody.Builder();
        for ( String key : params.keySet() )
        {
            builder.add( key, params.get( key ) );
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url( url ).put( body ).build();
        mOkHttpClent.newCall( request ).enqueue( callback );
    }

    private void delete( String url, Map<String, String> params, Callback callback )
    {
        FormBody.Builder builder = new FormBody.Builder();
        for ( String key : params.keySet() )
        {
            builder.add( key, params.get( key ) );
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url( url ).delete( body ).build();
        mOkHttpClent.newCall( request ).enqueue( callback );
    }

    public static class Error
    {
        private int code;
        private String msg;

        public void setCode(int code)
        {
            this.code = code;
        }

        public void setMsg(String msg)
        {
            this.msg = msg;
        }

        public int getCode()
        {
            return code;
        }

        public String getMsg()
        {
            return msg;
        }
    }

    private static class ErrorEntity
    {
        public Error error;

        public ErrorEntity()
        {
            error = new Error();
        }
    }

    /**
     * 静态内部类
     */
    public static class Holder
    {
        private static final HttpManage INSTANCE = new HttpManage();
    }
}
