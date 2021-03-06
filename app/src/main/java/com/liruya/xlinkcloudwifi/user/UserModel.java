package com.liruya.xlinkcloudwifi.user;

import android.content.Context;
import android.support.annotation.NonNull;

import com.liruya.xlinkcloudwifi.Constant;
import com.liruya.xlinkcloudwifi.http.HttpManage;
import com.liruya.xlinkcloudwifi.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liruya on 2017/1/20.
 */

public class UserModel implements IUserModel
{
    private static UserModel instance = null;
    private Context mContext;

    private UserModel ( Context context )
    {
        mContext = context;
    }

    public static UserModel getInstance( Context context )
    {
        if ( instance == null )
        {
            instance = new UserModel( context );
        }
        return instance;
    }

    @Override
    public void saveUserInfo ( UserInfo info )
    {
        PreferenceUtil.setPrefer( mContext, Constant.LOCAL_USER_FILENAME, info.getEmail(), Constant.LOCAL_USER_EMAIL_KEY );
        PreferenceUtil.setPrefer( mContext, Constant.LOCAL_USER_FILENAME, info.getPassword(), Constant.LOCAL_USER_PASSWORD_KEY );
    }

    @Override
    public void loadUserInfo ( @NonNull LoadUserInfoCallback callback )
    {
        Object email = PreferenceUtil.getPrefer( mContext, Constant.LOCAL_USER_FILENAME, Constant.LOCAL_USER_EMAIL_KEY, null );
        Object password = PreferenceUtil.getPrefer( mContext, Constant.LOCAL_USER_FILENAME, Constant.LOCAL_USER_PASSWORD_KEY, null );
        if ( email != null && password != null && email instanceof String && password instanceof String )
        {
            callback.onUserInfoLoaded( new UserInfo( (String) email, Constant.COMPANY_ID, (String) password, Constant.SOURCE_ANDROID ) );
        }
        else
        {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void loginUser ( UserInfo info, @NonNull final LoginCallBack callBack )
    {
        HttpManage.getInstance().login( info.getEmail(), info.getPassword(), new Callback() {
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
}
