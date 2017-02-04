package com.liruya.xlinkcloudwifi.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liruya.xlinkcloudwifi.Constant;
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
        initEvent();
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
    }

    @Override
    protected void initEvent ()
    {
        login_btn_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View view )
            {
                String email = getEmail();
                String password = getPassword();
                if ( isValidEmail( email ) && isValidPassword( password) )
                {
                    mPresenter.registerUser( new UserInfo( email, Constant.COMPANY_ID, password ), new UserContract.Presenter.RegisterUserCallback() {
                        @Override
                        public void onRegisterSuccess ()
                        {

                        }

                        @Override
                        public void onRegisterFailure ( HttpError error )
                        {

                        }
                    } );
                }
            }
        } );

        login_btn_signin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View view )
            {
                String email = getEmail();
                String password = getPassword();
                if ( isValidEmail( email ) && isValidPassword( password ) )
                {
                }
            }
        } );
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
    public void openLoginActivity ()
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

    private boolean isValidEmail( String email )
    {
        if ( TextUtils.isEmpty( email ) )
        {
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches();
    }

    private boolean isValidPassword( String password )
    {
        return !TextUtils.isEmpty( password ) && (password.length() > 6);
    }
}
