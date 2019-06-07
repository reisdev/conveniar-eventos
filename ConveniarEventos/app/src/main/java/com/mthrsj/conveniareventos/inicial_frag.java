package com.mthrsj.conveniareventos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mthrsj.conveniareventos.Adapter.EventAdapter;
import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Category;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class inicial_frag extends Fragment {

    static Foundation fnd;
    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;

    public static inicial_frag newInstance(final Foundation f) {
        fnd = new Foundation(f);
        return new inicial_frag();
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
    public void onViewCreated(View view, Bundle savedInstanceState) {

        eventList = new ArrayList<>();

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getApiRequestCategories(fnd.getURLS().getEventos());
        updateEventsList();
    }

    private void updateEventsList() {
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);
    }

    private void getApiRequestCategories(final String domain) {
        String url = domain + "/api/";
        //TODO: trocar o api_url para o url recebido de dominio, qnd o acesso for liberado
        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
        Call<List<Category>> allCategory = apiService.getCategories();
        allCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                if (response.isSuccessful()) {
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

    private void getEventByCategory(List<Category> categoriesRes) {
        String[] categories = new String[categoriesRes.size()];
        for (int i = 0; i < categoriesRes.size(); i++) {
            //TODO: trocar o api_url para o url recebido de dominio, qnd o acesso for liberado
            ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
            Call<List<Event>> eventByCategory = apiService.getEvents(Integer.toString(categoriesRes.get(i).getCodEventoCategoria()));
            eventByCategory.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(Call<List<Event>> call, retrofit2.Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        for (int j = 0; j < response.body().size(); j++) {
                            Event e = new Event(response.body().get(j));
                            eventList.add(e);
                        }
                        updateEventsList();
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        fnd = null;
    }
}
