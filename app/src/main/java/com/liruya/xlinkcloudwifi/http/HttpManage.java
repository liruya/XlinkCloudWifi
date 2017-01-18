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
    public final String shareListUrl = host + "/v2/share/device/list?Access-Token={access_token}";
    public final String getUserInfoUrl = host + "/v2/user/{user_id}?Access-Token={access_token}";
    //获取某个用户绑定的设备列表。
    public final String subscribeListUrl = host + "/v2/user/{user_id}/subscribe/devices";
    //设备管理员分享设备给指定用户
    public final String shareDeviceUrl = host + "/v2/share/device?Access-Token={access_token}";
    //用户拒绝设备分享
    public final String denyShareUrl = host + "/v2/share/device/deny?Access_Token={access_token}";
    //用户确认设备分享
    public final String acceptShareUrl = host + "/v2/share/device/accept?Access_Token={access_token}";
    //获取设备信息
    public final String getDeviceUrl = host + "/v2/product/{product_id}/device/{device_id}?Access_Token={access_token}";
    //订阅设备（待定）
    public final String subscribeUrl = host + "/v2/user/{user_id}/subscribe?Access-Token={access_token}";
    //修改用户信息
    public final String modifyUserUrl = host + "/v2/user/{user_id}?Access-Token={access_token}";
    //重置密码
    public final String resetPasswordUrl = host + "/v2/user/password/reset?Access-Token={access_token}";
    //获取数据端点列表
    public final String getDatapointsUrl = host + "/v2/product/{product_id}/datapoints?Access-Token={access_token}";
    //取消订阅设备
    public final String unsubscribeUrl = host + "/v2/user/{user_id}/unsubscribe?Access-Token={access_token}";

    //.管理员或用户删除这条分享记录
    public final String deleteShareUrl = host + "/v2/share/device/delete/{invite_code}?Access-Token={access_token}";

    //检查固件版本
    public final String checkUpdateUrl = "http://app.xlink.cn/v1/user/device/version";
    //固件升级
    public final String upgradeUrl = "http://app.xlink.cn/v1/user/device/upgrade";

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
     * http 邮箱登录接口
     *
     * @param mail 用户 邮箱
     * @param pwd  密码
     */
    public void login(String mail, String pwd, final Callback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("email", mail);
        params.put("corp_id", COMPANY_ID);
        params.put("password", pwd);
        post(loginUrl, params, callback);
    }

    /**
     * http //.管理员（用户）获取所有设备分享请求列表
     */
    public void getShareList(String access_token, final Callback callback) {
        String url = shareListUrl.replace( "{access_token}", access_token );
        get(url, callback);
    }

    /**
     * 获取用户详细信息
     */
    public void getUserInfo(int userId, String access_token, final Callback callback)
    {
        String url = getUserInfoUrl.replace("{user_id}", userId + "").replace( "{access_token}", access_token );
        get(url, callback);
    }

    /**
     * .修改用户信息
     *
     * @param userId userId
     */
    public void modifyUser(int userId, String nickname, String access_token, final Callback callback) {
        String url = modifyUserUrl.replace("{user_id}", userId + "").replace( "{access_token}", access_token );
        Map<String, String> params = new HashMap<>();
        params.put("nickname", nickname);
        put(url, params, callback);
    }

    /**
     * http 忘记密码
     *
     * @param mail 用户 邮箱
     */
    public void forgetPasswd(String mail, final Callback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("email", mail);
        params.put("corp_id", COMPANY_ID);
        post(forgetUrl, params, callback);
    }

    /**
     * .重置密码
     */
    public void resetPassword(String newPasswd, String oldPasswd, String access_token, final Callback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("old_password", oldPasswd);
        params.put("new_password", newPasswd);
        String url = resetPasswordUrl.replace( "{access_token}", access_token );
        put(url, params, callback);
    }

    /**
     * 获取数据端点信息
     */
    public void getDatapoints(String pid, String access_token, final Callback callback)
    {
        String url = getDatapointsUrl.replace("{product_id}", pid).replace( "{access_token}", access_token );
        get(url, callback);
    }

    /**
     * 设备管理员分享设备给指定用户
     *
     * @param mail 用户 邮箱
     */
    public void shareDevice(String mail, int deviceId, String access_token, final Callback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("user", mail);
        params.put("expire", "7200");
        params.put("mode", "email");
        params.put("device_id", deviceId + "");
        String url = shareDeviceUrl.replace( "{access_token}", access_token );
        post(url, params, callback);
    }

    /**
     * 用户接受设备分享
     *
     * @param inviteCode 分享ID
     */
    public void acceptShare (String inviteCode, String access_token, final Callback callback )
    {
        Map<String, String> params = new HashMap<>();
        params.put("invite_code", inviteCode);
        String url = acceptShareUrl.replace( "{access_token}", access_token  );
        post(url, params, callback);
    }

    /**
     * 用户拒绝设备分享
     *
     * @param inviteCode 分享ID
     */
    public void denyShare(String inviteCode, String access_token, final Callback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("invite_code", inviteCode);
        String url = denyShareUrl.replace( "{access_token}", access_token );
        post(denyShareUrl, params, callback);
    }

    /**
     * 订阅设备
     *
     * @param userId userId
     */
    public void subscribe(String userId, String productId, int deviceId, String access_token, final Callback callback)
    {
        String url = subscribeUrl.replace("{user_id}", userId).replace( "{access_token}", access_token );
        Map<String, String> params = new HashMap<>();
        params.put("product_id", productId);
        params.put("device_id", deviceId + "");
        post(url, params, callback);
    }

    /**
     * 取消订阅设备
     *
     * @param userId userId
     */
    public void unsubscribe(int userId, int deviceId, String access_token, final Callback callback)
    {
        String url = unsubscribeUrl.replace("{user_id}", userId+"").replace( "{access_token}", access_token );
        Map<String, String> params = new HashMap<>();
        params.put("device_id", deviceId + "");
        post(url, params, callback);
    }

    /**
     * 获取设备信息
     *
     * @param deviceId 设备ID
     */
    public void getDevice(String productId, int deviceId, String access_token, final Callback callback)
    {
        String url = getDeviceUrl.replace("{device_id}", deviceId + "")
                                 .replace("{product_id}", productId)
                                 .replace( "{access_token}", access_token );
        get(url, callback);
    }

    /**
     * http //.获取某个用户绑定的设备列表。
     */
    public void getSubscribeList(int userId, String access_token, final Callback callback) {
        String url = subscribeListUrl.replace("{user_id}", userId+"").replace( "{access_token}", access_token );
        get(url, callback);
    }

    /**
     * 删除分享
     */
    public void deleteShare(String inviteCode, String access_token, final Callback callback) {
        String url = deleteShareUrl.replace("{invite_code}", inviteCode).replace( "{access_token}", access_token );
        delete(url, null, callback);
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
