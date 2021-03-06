package com.mthrsj.conveniareventos.Utils.API;

import android.text.TextUtils;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConveniarAPI {
    private static String BASE_URL = "https://apieventos.conveniar.com.br/conveniar/api/";
    private static Retrofit rfit = null;

    public static Retrofit getClient() {
        if (rfit == null) {
            rfit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rfit;
    }

    public static Retrofit getClient(String API_URL) {
        Log.d("REQ", "Request URL:" + API_URL);
        if (rfit == null) {
            rfit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rfit;
    }

    public static void closeClient() {
        Log.d("CON","Retrofit Connection Closed");
        rfit = null;
    }
}
