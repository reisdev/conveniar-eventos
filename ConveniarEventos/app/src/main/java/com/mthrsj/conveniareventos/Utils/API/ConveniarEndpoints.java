package com.mthrsj.conveniareventos.Utils.API;

import com.mthrsj.conveniareventos.Utils.API.models.Auth;
import com.mthrsj.conveniareventos.Utils.API.models.Category;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.API.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

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

    @GET("eventos/ids")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<List<Event>> getEventsById(@Query("codEventos") List<Integer> query);

    @GET("eventos")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<List<Event>> getEventsBySearchName(@Query("query") String query);

    @GET("eventos/oauth/token")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<Auth> authenticate(@Header("Authorization") String credentials);

    @GET("eventos/usuario")
    @Headers("X-API-KEY: 7e61b6bb-6841-415f-954e-5e2ba445cc7c")
    Call<User> getUsuario(@Header("Authorization") String auth);
}
