package com.liruya.xlinkcloudwifi.user;

import android.support.annotation.NonNull;

/**
 * Created by liruya on 2017/1/19.
 */

public interface IUserModel
{
    interface LoadUserInfoCallback
    {
        void onDataNotAvailable();
        void onUserInfoLoaded( UserInfo info );
    }
    void saveUserInfo( UserInfo info );
    void loadUserInfo( @NonNull LoadUserInfoCallback callback );
}
