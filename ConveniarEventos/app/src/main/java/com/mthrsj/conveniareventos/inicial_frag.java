package com.mthrsj.conveniareventos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mthrsj.conveniareventos.Adapter.EventAdapter;
import com.mthrsj.conveniareventos.models.Category;
import com.mthrsj.conveniareventos.models.Event;
import com.mthrsj.conveniareventos.models.Foundation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicial_frag extends Fragment {

    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;
    static Foundation fnd;

    public static inicial_frag newInstance(final Foundation f) {
        fnd = new Foundation(f);
        inicial_frag fragment = new inicial_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inicial_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        eventList = new ArrayList<>();

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getApiRequestCategories(fnd.getDomain());

        Category a = new Category(11, "asdd");
        eventList.add(new Event (
                11,
                "asdad",
                "123",
                "123",
                a
            )
        );

        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);

    }

    /*public void getApiRequestCategories(final String domain){
        String url = domain + "/api/";
        //TODO: trocar o api_url para o url recebido de dominio, qnd o acesso for liberado
        try {
            ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
            Call<List<Category>> allCategories = apiService.getCategories();
            List<Category> catergories = allCategories.execute().body();
            for(int i=0; i<catergories.size(); i++){
                Log.d("CAT", catergories.get(i).getNomeEventoCategoria());
            }
        } catch (IOException e) {
            Log.d("ERR", e.getMessage());
        }
    }*/

    public void getApiRequestCategories(final String domain){
        String url = domain + "/api/";
        //TODO: trocar o api_url para o url recebido de dominio, qnd o acesso for liberado
        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
        Call<List<Category>> allCategory = apiService.getCategories();
        allCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                if(response.isSuccessful()){
                    getEventByCategory(response.body());
                } else {
                    Log.e("REQ", "Request of categories failed:" + response.raw().body());
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("REQ", "Could not connect to API");
            }
        });
        ConveniarAPI.closeClient();
    }

    public void getEventByCategory(List<Category> response){
        String[] categories = new String[response.size()];
        for(int i =0; i<response.size(); i++){
            Log.d("FND", response.get(i).getNomeEventoCategoria());
            //TODO: trocar o api_url para o url recebido de dominio, qnd o acesso for liberado
            ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
            Call<List<Event>> eventByCategory = apiService.getEvents(Integer.toString(response.get(i).getCodEventoCategoria()));
            eventByCategory.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(Call<List<Event>> call, retrofit2.Response<List<Event>> response) {
                    if(response.isSuccessful()){
                        for(int j=0; j<response.body().size(); j++){
                            Log.d("EVT", response.body().get(j).getNomeEvento());
                            Event e = new Event(response.body().get(j));
                            eventList.add(new Event(e));
                        }
                    } else {
                        Log.e("REQ", "Request of events by categories failed:" + response.raw().body());
                    }
                }
                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                }
            });
        }
        ConveniarAPI.closeClient();
    }
}
