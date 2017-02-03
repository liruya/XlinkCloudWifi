package com.liruya.xlinkcloudwifi.user;

import com.liruya.xlinkcloudwifi.http.HttpManage;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liruya on 2017/1/19.
 */

public class UserPresenter implements UserContract.Presenter
{
    private UserModel mUserModel;
    private UserContract.View mView;

    public UserPresenter ( UserModel userModel, UserContract.View view )
    {
        mUserModel = userModel;
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
                      }
                  } );
    }

    @Override
    public void saveUserLocal ( UserInfo info )
    {
        mUserModel.saveUserInfo( info );
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

                      }
                  } );
    }

    @Override
    public void start ()
    {
        mUserModel.loadUserInfo( new IUserModel.LoadUserInfoCallback()
        {
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
}
