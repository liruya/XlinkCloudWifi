package com.liruya.xlinkcloudwifi.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liruya.xlinkcloudwifi.R;
import com.liruya.xlinkcloudwifi.activity.BaseActivity;
import com.liruya.xlinkcloudwifi.activity.MainActivity;
import com.liruya.xlinkcloudwifi.bean.HttpError;

public class LoginActivity extends BaseActivity implements UserContract.View
{
    private static final String TAG = "LoginActivity";

    private EditText login_et_email;
    private EditText login_et_password;
    private Button login_btn_signup;
    private Button login_btn_signin;
    private TextView login_tv_forget;
    private TextView login_tv_skip;
    private ProgressBar login_progress;

    private UserContract.Presenter mPresenter;

    @Override
    protected void onCreate ( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        initView();
    }

    @Override
    protected void initView ()
    {
        login_et_email = (EditText) findViewById( R.id.login_et_email );
        login_et_password = (EditText) findViewById( R.id.login_et_password );
        login_btn_signup = (Button) findViewById( R.id.login_btn_signup );
        login_btn_signin = (Button) findViewById( R.id.login_btn_signin );
        login_tv_forget = (TextView) findViewById( R.id.login_tv_forget );
        login_tv_skip = (TextView) findViewById( R.id.login_tv_skip );
        login_progress = (ProgressBar) findViewById( R.id.login_progress );
    }

    @Override
    protected void initData ()
    {
        UserModel.getInstance( this ).loadUserInfo( new IUserModel.LoadUserInfoCallback() {
            @Override
            public void onDataNotAvailable ()
            {

            }

            @Override
            public void onUserInfoLoaded ( UserInfo info )
            {

            }
        } );
    }

    @Override
    protected void initEvent ()
    {

    }

    @Override
    public String getEmail ()
    {
        return login_et_email.getText().toString();
    }

    @Override
    public String getPassword ()
    {
        return login_et_password.getText().toString();
    }

    @Override
    public void showProgress ( boolean show )
    {
        if ( show )
        {
            login_progress.setVisibility( View.VISIBLE );
        }
        else
        {
            login_progress.setVisibility( View.INVISIBLE );
        }
    }

    @Override
    public void showError ( HttpError error )
    {

    }

    @Override
    public void openMainActivity ()
    {
        finish();
        Intent intent = new Intent( LoginActivity.this, MainActivity.class );
        startActivity( intent );
    }

    @Override
    public void setPresenter ( UserContract.Presenter presenter )
    {
        mPresenter = presenter;
    }
}
