package com.liruya.xlinkcloudwifi.user;

import com.liruya.xlinkcloudwifi.base.IBasePresenter;
import com.liruya.xlinkcloudwifi.base.IBaseView;
import com.liruya.xlinkcloudwifi.bean.HttpError;

/**
 * Created by liruya on 2017/1/19.
 */

public interface UserContract
{
    interface Presenter extends IBasePresenter
    {
        interface RegisterUserCallback
        {
            void onRegisterSuccess();
            void onRegisterFailure( HttpError error );
        }
        interface RequestAuthorizeCallback
        {
            void onRequestSuccess();
            void onRequestFailure( HttpError error );
        }
        void registerUser( UserInfo info, RegisterUserCallback callback );
        void saveUserLocal( UserInfo info );
        void requestAuthorize( UserInfo info, RequestAuthorizeCallback callBack );
    }

    interface View extends IBaseView<Presenter>
    {
        String getEmail();
        String getPassword();
        void showProgress( boolean show );
        void showError(HttpError error);
        void openMainActivity();
    }
}
