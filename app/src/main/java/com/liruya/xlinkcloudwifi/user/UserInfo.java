package com.liruya.xlinkcloudwifi.user;

/**
 * Created by liruya on 2017/1/19.
 */

public class UserInfo
{
    private String email;
    private String corp_id;
    private String password;
    private String source;

    public UserInfo ( String email, String corp_id, String password, String source )
    {
        this.email = email;
        this.corp_id = corp_id;
        this.password = password;
        this.source = source;
    }

    public UserInfo ( String email, String corp_id, String password )
    {
        this.email = email;
        this.corp_id = corp_id;
        this.password = password;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail ( String email )
    {
        this.email = email;
    }

    public String getCorp_id ()
    {
        return corp_id;
    }

    public void setCorp_id ( String corp_id )
    {
        this.corp_id = corp_id;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword ( String password )
    {
        this.password = password;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource ( String source )
    {
        this.source = source;
    }
}
