package com.liruya.xlinkcloudwifi.user;

import android.content.Context;

import com.liruya.xlinkcloudwifi.bean.HttpError;
import com.liruya.xlinkcloudwifi.http.HttpManage;
import com.liruya.xlinkcloudwifi.util.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by liruya on 2017/1/19.
 */

public class UserPresenter implements UserContract.Presenter
{
    private Context mContext;
    private UserContract.View mView;

    public UserPresenter ( Context context )
    {
        mContext = context;
    }

    public UserPresenter ( Context context, UserContract.View view )
    {
        mContext = context;
        mView = view;
        mView.setPresenter( this );
    }

    @Override
    public void registerUser ( UserInfo info, RegisterUserCallback callback )
    {
        HttpManage.getInstance()
                  .registerUserByMail( info.getEmail(), info.getPassword(), new Callback()
                  {
                      @Override
                      public void onFailure ( Call call, IOException e )
                      {

                      }

                      @Override
                      public void onResponse ( Call call, Response response ) throws IOException
                      {
                          String json = response.body()
                                                .string();
                          LogUtil.d( "tag", json );
                      }
                  } );
    }

    @Override
    public void saveUserLocal ( UserInfo info )
    {
        UserModel.getInstance( mContext )
                 .saveUserInfo( info );
    }

    @Override
    public void requestAuthorize ( UserInfo info, RequestAuthorizeCallback callBack )
    {
        HttpManage.getInstance()
                  .login( info.getEmail(), info.getPassword(), new Callback()
                  {
                      @Override
                      public void onFailure ( Call call, IOException e )
                      {

                      }

                      @Override
                      public void onResponse ( Call call, Response response ) throws IOException
                      {
                          Headers headers = response.headers();
                          String json = response.body().toString();
                          LogUtil.d( "123", headers.name( 0 ) );
                      }
                  } );
    }

    @Override
    public void start ()
    {
        UserModel.getInstance( mContext )
                 .loadUserInfo( new IUserModel.LoadUserInfoCallback()
                 {
                     @Override
                     public void onDataNotAvailable ()
                     {
                         mView.openLoginActivity();
                     }

                     @Override
                     public void onUserInfoLoaded ( final UserInfo info )
                     {
                        requestAuthorize( info, new RequestAuthorizeCallback() {
                            @Override
                            public void onRequestSuccess ()
                            {
                                UserModel.getInstance( mContext ).saveUserInfo( info );
                            }

                            @Override
                            public void onRequestFailure ( HttpError error )
                            {

                            }
                        } );
                     }
                 } );
    }
}
