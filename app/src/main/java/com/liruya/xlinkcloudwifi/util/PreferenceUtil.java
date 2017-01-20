package com.liruya.xlinkcloudwifi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liruya on 2017/1/20.
 */

public class PreferenceUtil
{
    /**
     * 储存对象到SharedPreference
     * @param context
     * @param preferName    文件名
     * @param object        对象
     * @param key
     */
    public static void setObjectToPrefer ( Context context, String preferName, Object object, String key )
    {
        SharedPreferences objectPrefer
        = context.getSharedPreferences( preferName, Context.MODE_PRIVATE );
        if ( object == null )
        {
            SharedPreferences.Editor editor = objectPrefer.edit().remove( key );
            SharedPreferencesCompat.EditorCompat.getInstance().apply( editor );
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream( baos );
            oos.writeObject( object );
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        String objectStr = new String( Base64.encode( baos.toByteArray(), Base64.DEFAULT ));
        try
        {
            baos.close();
            oos.close();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = objectPrefer.edit().putString( key, objectStr );
        SharedPreferencesCompat.EditorCompat.getInstance().apply( editor );
    }

    /**
     * 从指定文件获取对象
     * @param context
     * @param preferName   文件名
     * @param key
     * @return
     */
    public static Object getObjectFromPrefer(Context context, String preferName, String key)
    {
        SharedPreferences objectPrefer = context.getSharedPreferences( preferName, Context.MODE_PRIVATE );
        String objectStr = objectPrefer.getString( key, "" );
        if ( objectStr == null || objectStr.equals( "" ) )
        {
            return  null;
        }
        byte[] objBytes = Base64.decode( objectStr.getBytes(), Base64.DEFAULT );
        ByteArrayInputStream bais = new ByteArrayInputStream( objBytes );
        try
        {
            ObjectInputStream ois = new ObjectInputStream( bais );
            Object object = ois.readObject();
            bais.close();
            ois.close();
            return object;
        } catch ( IOException e )
        {
            e.printStackTrace();
        } catch ( ClassNotFoundException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件中储存的对象
     * @param context
     * @param preferName
     * @param key
     */
    public static void deleteObjectFromPrefer( Context context, String preferName, String key)
    {
        SharedPreferences objectPrefer
        = context.getSharedPreferences( preferName, Context.MODE_PRIVATE );

        if ( objectPrefer.contains( key ) )
        {
            SharedPreferences.Editor editor = objectPrefer.edit().remove( key );
            SharedPreferencesCompat.EditorCompat.getInstance().apply( editor );
        }
    }
}
