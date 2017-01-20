package com.liruya.xlinkcloudwifi.bean;

/**
 * Created by liruya on 2017/1/19.
 */

public class LoginInfo
{
    private long user_id;
    private String access_token;
    private String refresh_token;
    private int expire_in;
    private String authorize;

    public LoginInfo ( long user_id, String access_token, String refresh_token, int expire_in, String authorize )
    {
        this.user_id = user_id;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.expire_in = expire_in;
        this.authorize = authorize;
    }

    public LoginInfo ( long user_id, String access_token, String authorize )
    {
        this.user_id = user_id;
        this.access_token = access_token;
        this.authorize = authorize;
    }

    public long getUser_id ()
    {
        return user_id;
    }

    public void setUser_id ( long user_id )
    {
        this.user_id = user_id;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token ( String access_token )
    {
        this.access_token = access_token;
    }

    public String getRefresh_token ()
    {
        return refresh_token;
    }

    public void setRefresh_token ( String refresh_token )
    {
        this.refresh_token = refresh_token;
    }

    public int getExpire_in ()
    {
        return expire_in;
    }

    public void setExpire_in ( int expire_in )
    {
        this.expire_in = expire_in;
    }

    public String getAuthorize ()
    {
        return authorize;
    }

    public void setAuthorize ( String authorize )
    {
        this.authorize = authorize;
    }
}
