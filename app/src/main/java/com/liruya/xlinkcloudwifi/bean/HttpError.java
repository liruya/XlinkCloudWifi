package com.liruya.xlinkcloudwifi.bean;

/**
 * Created by liruya on 2017/1/20.
 */

public class HttpError
{
    private int code;
    private String msg;

    public HttpError ( int code, String msg )
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode ()
    {
        return code;
    }

    public void setCode ( int code )
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg ( String msg )
    {
        this.msg = msg;
    }
}
