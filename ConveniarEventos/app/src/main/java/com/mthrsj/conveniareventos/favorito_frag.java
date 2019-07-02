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
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.Database.Database;
import com.mthrsj.conveniareventos.Utils.Database.Models.Config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.Realm;
import io.realm.RealmObjectChangeListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favorito_frag extends Fragment {
    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    EventAdapter adapter;

    public static favorito_frag newInstance() {
        favorito_frag fragment = new favorito_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorito_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getFavoriteEvents();
        updateEventsList();
    }

    private void updateEventsList() {
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void getFavoriteEvents() {
        eventList.clear();
        try {
            Realm db = Database.getInstance();
            db.beginTransaction();
            /*db.where(Config.class).equalTo("name", "favorites").findFirst().deleteFromRealm();
            db.commitTransaction();*/
            final Config favConfig = db.where(Config.class).equalTo("name", "favorites").findFirst();
            db.commitTransaction();
            favConfig.addChangeListener(new RealmObjectChangeListener<Config>() {
                @Override
                public void onChange(Config config, @Nullable ObjectChangeSet changeSet) {
                    String[] favS = config.getValue().split("\\s+");
                    List<Integer> query = new ArrayList<>();
                    for (int i = 0; i < favS.length; i++) {
                        if (!favS[i].isEmpty()) query.add(Integer.parseInt(favS[i]));
                    }
                    favoriteEventsRequest(query);
                }
            });

            String[] favS = favConfig.getValue().split("\\s+");
            if (favConfig.getValue().length() == 0) {
                return;
            }
            List<Integer> query = new ArrayList<>();
            for (int i = 0; i < favS.length; i++) {
                Log.d("FAV", favS[i]);
                if (!favS[i].isEmpty()) query.add(Integer.parseInt(favS[i]));
            }
            favoriteEventsRequest(query);
        } catch (NullPointerException e) {

        }
    }

    public void favoriteEventsRequest(List<Integer> query) {
        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://apieventos.conveniar.com.br/conveniar/api/").create(ConveniarEndpoints.class);
        final Call<List<Event>> events = apiService.getEventsById(query);
        events.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()) {
                    eventList.clear();
                    eventList.addAll(response.body());
                    updateEventsList();
                } else {
                    Log.e("REQ", "Request of events failed: " + response.raw().body());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("REQ", "Request of events failed:" + t.toString());
            }
        });
        ConveniarAPI.closeClient();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
