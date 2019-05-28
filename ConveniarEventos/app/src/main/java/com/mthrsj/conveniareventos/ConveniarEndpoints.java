package com.mthrsj.conveniareventos;

import com.mthrsj.conveniareventos.models.Foundation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConveniarEndpoints {
    @GET("fundacoes")
    Call<List<Foundation>> getFoundations();
}
