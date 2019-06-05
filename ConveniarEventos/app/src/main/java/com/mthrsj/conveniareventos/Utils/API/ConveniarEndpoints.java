package com.mthrsj.conveniareventos.Utils.API;

import com.mthrsj.conveniareventos.Utils.API.models.Category;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConveniarEndpoints {
    @GET("fundacoes")
    Call<List<Foundation>> getFoundations();

    @GET("eventos/categorias")
    Call<List<Category>> getCategories();

    @GET("eventos/{codCategoria}")
    Call<List<Event>> getEvents(@Path("codCategoria") String codCategoria);
}
