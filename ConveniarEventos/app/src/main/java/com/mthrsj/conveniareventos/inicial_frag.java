package com.mthrsj.conveniareventos;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mthrsj.conveniareventos.Adapter.EventAdapter;
import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class inicial_frag extends Fragment {

    static Foundation fnd;
    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;
    private static String[] EventosSearchBar;

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

        Log.d("DEB", fnd.getURLS().toString());

        getEvents();
        updateEventsList();


    }

    private void updateEventsList() {
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);

        if(EventosSearchBar != null){
            try {
                ArrayAdapter<String> adptSearchBar = new ArrayAdapter<String>(this.getContext(), android.R.layout.select_dialog_item, EventosSearchBar);

                AutoCompleteTextView searchBar = getActivity().findViewById(R.id.searchBar);
                searchBar.setAdapter(adptSearchBar);

                searchBar.setOnEditorActionListener(searchListener);
            }
            catch (NullPointerException e) {

            }
        } else {

        }
    }

    private TextView.OnEditorActionListener searchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch(actionId){
                case IME_ACTION_SEARCH:
                    search_frag search = new search_frag();
                    FragmentManager fm = getFragmentManager();

                    fm.beginTransaction().replace(R.id.frame_layout, search.newInstance(fnd, v.getText().toString())).commit();

                    break;
            }
            return false;
        }
    };

    private void getEvents() {
        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://apieventos.conveniar.com.br/conveniar/api/").create(ConveniarEndpoints.class);
        final Call<List<Event>> events = apiService.getEvents();
        events.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()) {
                    if(response.body() != null){
                        EventosSearchBar = new String[response.body().size()];
                        eventList.addAll(response.body());
                        for(int i=0; i < response.body().size(); i++){
                            EventosSearchBar[i] = (response.body().get(i).getNomeEvento());
                            Log.d("SEARCH", EventosSearchBar[i]);
                        }
                    }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        fnd = null;
    }
}
