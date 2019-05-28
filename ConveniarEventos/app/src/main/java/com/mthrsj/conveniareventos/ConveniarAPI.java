package com.mthrsj.conveniareventos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConveniarAPI {
    private static String BASE_URL = "https://servicos.conveniar.com.br/adminservico/api/";
    private static Retrofit rfit = null;

    public static Retrofit getClient(){
        if(rfit == null){
            rfit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rfit;
    }

    public static Retrofit getClient(String API_URL){
        if(rfit == null){
            rfit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rfit;
    }
}
