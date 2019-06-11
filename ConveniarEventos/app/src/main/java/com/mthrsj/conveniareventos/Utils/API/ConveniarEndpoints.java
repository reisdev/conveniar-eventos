package com.mthrsj.conveniareventos.Utils.API;

import com.mthrsj.conveniareventos.Utils.API.models.Category;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ConveniarEndpoints {
    @GET("fundacoes")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<List<Foundation>> getFoundations();

    @GET("eventos/categorias")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<List<Category>> getCategories();

    @GET("eventos")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<List<Event>> getEvents();
}
