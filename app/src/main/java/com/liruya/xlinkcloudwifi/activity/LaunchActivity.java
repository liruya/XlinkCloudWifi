package com.liruya.xlinkcloudwifi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.liruya.xlinkcloudwifi.R;
import com.liruya.xlinkcloudwifi.user.LoginActivity;

public class LaunchActivity extends BaseActivity
{

    @Override
    protected void onCreate ( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_launch );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run ()
            {
                Intent intent;
                intent = new Intent( LaunchActivity.this, LoginActivity.class );
            }
        }, 1000);
    }

    @Override
    protected void initView ()
    {

    }

    @Override
    protected void initData ()
    {

    }

    @Override
    protected void initEvent ()
    {

    }
}
