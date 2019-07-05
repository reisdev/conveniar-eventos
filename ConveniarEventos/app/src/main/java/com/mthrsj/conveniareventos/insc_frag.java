package com.mthrsj.conveniareventos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mthrsj.conveniareventos.Adapter.EventAdapter;
import com.mthrsj.conveniareventos.Adapter.SubscriptionAdapter;
import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Subscription;
import com.mthrsj.conveniareventos.Utils.Session;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insc_frag extends Fragment {

    RecyclerView recyclerView;
    List<Subscription> subscriptionList = new ArrayList<>();
    SubscriptionAdapter adapter;
    Session session;

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
        session = new Session(getActivity().getApplicationContext());
        return inflater.inflate(R.layout.fragment_insc_frag, container, false);
    }

    private void updateEventsList() {
        adapter = new SubscriptionAdapter(this.getContext(), subscriptionList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerView = getActivity().findViewById(R.id.subscriptions_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(session != null)
            if(session.isLogged())
                getSubscriptedEvents();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(session != null) {
            if (isVisibleToUser && session.isLogged()) {
                getSubscriptedEvents();
            }
        }
    }

    public void getSubscriptedEvents() {

        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://apieventos.conveniar.com.br/conveniar/api/").create(ConveniarEndpoints.class);

        final Call<List<Subscription>> events = apiService.getSubscriptions(session.getAuthToken());

        events.enqueue(new Callback<List<Subscription>>() {
            @Override
            public void onResponse(Call<List<Subscription>> call, Response<List<Subscription>> response) {
                if (response.isSuccessful()) {
                    subscriptionList.clear();
                    subscriptionList.addAll(response.body());
                    updateEventsList();
                } else {
                    Log.e("REQ", "Request of events failed: " + response.raw().body());
                }
            }

            @Override
            public void onFailure(Call<List<Subscription>> call, Throwable t) {
                Log.e("REQ", "Request of events failed:" + t.toString());
            }
        });
        ConveniarAPI.closeClient();
    }
}
