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
import com.mthrsj.conveniareventos.Utils.Session;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insc_frag extends Fragment {

    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    EventAdapter adapter;

    public static insc_frag newInstance() {
        insc_frag fragment = new insc_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insc_frag, container, false);
    }

    private void updateEventsList() {
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerView = getActivity().findViewById(R.id.subscriptions_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getSubscriptedEvents();
        updateEventsList();
    }

    public void getSubscriptedEvents() {

        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://apieventos.conveniar.com.br/conveniar/api/").create(ConveniarEndpoints.class);
        Session s = new Session(getActivity().getApplicationContext());

        final Call<List<Event>> events = apiService.getSubscriptions(s.getAuthToken());

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
}
