package com.example.simplereviewfilm.ListPhim;

import android.content.Context;
import android.content.SharedPreferences;

public class mSharedPreferences {
    private Context context;
    private static final String mshareTK = "mTaiKhoan";

    public mSharedPreferences(Context context) {
        this.context = context;
    }

    public void putString(String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mshareTK,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= sharedPreferences.edit();
        edit.putString(key,value);
        edit.apply();
    }

    public String getString(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mshareTK,Context.MODE_PRIVATE);
        String kq = sharedPreferences.getString(key, null);
        return kq;
    }

    public void clearShared(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mshareTK, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.apply();
    }
}
