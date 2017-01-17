package com.liruya.xlinkcloudwifi;

import android.app.Application;

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
    @Override
    public void onCreate ()
    {
        super.onCreate();
//        初始化Xlink SDK
        XlinkAgent.init( this );
        XlinkAgent.getInstance().addXlinkListener( this );
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
