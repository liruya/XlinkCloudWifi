package com.liruya.xlinkcloudwifi.base;

/**
 * Created by liruya on 2017/1/19.
 */

public interface IBaseView<T extends IBasePresenter>
{
    void setPresenter( T presenter );
}
