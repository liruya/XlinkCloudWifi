package com.liruya.xlinkcloudwifi;

import android.app.Application;

import com.liruya.xlinkcloudwifi.bean.HttpError;
import com.liruya.xlinkcloudwifi.bean.LoginInfo;
import com.liruya.xlinkcloudwifi.user.IUserModel;
import com.liruya.xlinkcloudwifi.user.UserInfo;
import com.liruya.xlinkcloudwifi.user.UserModel;
import com.liruya.xlinkcloudwifi.user.UserPresenter;

import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

/**
 * Created by liruya on 2017/1/17.
 */

public class MyApp extends Application implements XlinkNetListener
{
    private int user_id;
    private String authkey;
    private String accessToken;
    private UserPresenter mPresenter;

    @Override
    public void onCreate ()
    {
        super.onCreate();
//        初始化Xlink SDK
        XlinkAgent.init( this );
        XlinkAgent.getInstance().addXlinkListener( this );
        UserModel.getInstance( this ).loadUserInfo( new IUserModel.LoadUserInfoCallback() {
            @Override
            public void onDataNotAvailable ()
            {

            }

            @Override
            public void onUserInfoLoaded ( UserInfo info )
            {
                UserModel.getInstance( getApplicationContext() ).loginUser( info, new IUserModel.LoginCallBack() {
                    @Override
                    public void onLoginFailure ( HttpError error )
                    {

                    }

                    @Override
                    public void onLoginSuccess ( LoginInfo info )
                    {

                    }
                } );
            }
        } );
    }

    @Override
    public void onStart ( int i )
    {

    }

    @Override
    public void onLogin ( int i )
    {

    }

    @Override
    public void onLocalDisconnect ( int i )
    {

    }

    @Override
    public void onDisconnect ( int i )
    {

    }

    @Override
    public void onRecvPipeData ( short i, XDevice xDevice, byte[] bytes )
    {

    }

    @Override
    public void onRecvPipeSyncData ( short i, XDevice xDevice, byte[] bytes )
    {

    }

    @Override
    public void onDeviceStateChanged ( XDevice xDevice, int i )
    {

    }

    @Override
    public void onDataPointUpdate ( XDevice xDevice, List< DataPoint > list, int i )
    {

    }

    @Override
    public void onEventNotify ( EventNotify eventNotify )
    {

    }
}
