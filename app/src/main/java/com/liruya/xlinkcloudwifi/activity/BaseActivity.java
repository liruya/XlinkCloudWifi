package com.liruya.xlinkcloudwifi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liruya on 2017/1/19.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    @Override
    protected void onStart ()
    {
        super.onStart();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
    }

    @Override
    protected void onStop ()
    {
        super.onStop();
    }

    @Override
    protected void onRestart ()
    {
        super.onRestart();
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy();
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initEvent();
}
