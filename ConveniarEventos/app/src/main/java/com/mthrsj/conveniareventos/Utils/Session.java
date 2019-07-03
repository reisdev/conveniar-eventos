package com.mthrsj.conveniareventos.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mthrsj.conveniareventos.Utils.API.models.User;

public class Session {

    private SharedPreferences prefs;

    public Session(Context ctx){
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void logIn(String username, String pwd, String authKey){
        prefs.edit().putString("username", username).commit();
        prefs.edit().putString("password", pwd).commit();
        prefs.edit().putString("auth", authKey).commit();
        prefs.edit().putBoolean("isLogged", true).commit();
    }

    public void logOut(){
        prefs.edit().putBoolean("isLogged", false).commit();
    }

    public Boolean isLogged(){
        return prefs.getBoolean("isLogged", false);
    }
}
