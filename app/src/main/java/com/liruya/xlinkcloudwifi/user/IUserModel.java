package com.liruya.xlinkcloudwifi.user;

import android.support.annotation.NonNull;

import com.liruya.xlinkcloudwifi.bean.HttpError;
import com.liruya.xlinkcloudwifi.bean.LoginInfo;

/**
 * Created by liruya on 2017/1/19.
 */

public interface IUserModel
{
    interface LoadUserInfoCallback
    {
        void onDataNotAvailable ();

        void onUserInfoLoaded ( UserInfo info );
    }

    interface LoginCallBack
    {
        void onLoginFailure ( HttpError error );

        void onLoginSuccess ( LoginInfo info );
    }

    void saveUserInfo ( UserInfo info );

    void loadUserInfo ( @NonNull LoadUserInfoCallback callback );

    void loginUser ( UserInfo info, @NonNull LoginCallBack callBack );
}
