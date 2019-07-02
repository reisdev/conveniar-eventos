package com.mthrsj.conveniareventos;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

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

public class search_frag extends Fragment {

    static String SearchParam;
    static Foundation fnd;
    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;
    private static String[] EventosSearchBar;
    AutoCompleteTextView autoCompleteTextView;

    public static search_frag newInstance(final Foundation f, final String param) {
        SearchParam = param;
        fnd = f;
        return new search_frag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_frag, container, false);
        Log.d("SEARCH", "SEARCH FRAGMENT OPENED");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        autoCompleteTextView = getActivity().findViewById(R.id.searchBar);
        autoCompleteTextView.setText(SearchParam);

        eventList = new ArrayList<>();

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getEvents();
        updateEventsList();

        TextView txt = getActivity().findViewById(R.id.voltar_inicial);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicial_frag frag = new inicial_frag();
                FragmentManager fm = getFragmentManager();

                fm.beginTransaction().replace(R.id.frame_layout, frag.newInstance(fnd)).commit();
            }
        });

    }

    public void updateEventsList(){
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);

        autoCompleteTextView.setOnEditorActionListener(searchListener);

        TextView textSearch = getActivity().findViewById(R.id.text_search);
        TextView textSearchName = getActivity().findViewById(R.id.text_search_name);
        int n = eventList.size();
        String txt = n + " resultados encontrados para";
        textSearch.setText(txt);
        textSearchName.setTypeface(Typeface.DEFAULT_BOLD);
        textSearchName.setText('"' + SearchParam + '"');
    }

    private TextView.OnEditorActionListener searchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch(actionId){
                case IME_ACTION_SEARCH:
                    Log.d("SEARCH", "SEARCH UPDATE");
                    SearchParam = autoCompleteTextView.getText().toString();
                    getEvents();
                    break;
            }
            return false;
        }
    };

    public void getEvents(){
        ConveniarEndpoints apiService = ConveniarAPI.getClient().create(ConveniarEndpoints.class);
        final Call<List<Event>> events = apiService.getEventsBySearchName(SearchParam);

        events.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    Log.d("SEARCH", "RESPONSE IS SUCCESSFULL");

                    eventList = new ArrayList<>();
                    eventList.addAll(response.body());
                    for(int i=0; i< response.body().size(); i++){
                        Log.d("SEARCH", response.body().get(i).getNomeEvento());
                    }

                    updateEventsList();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.d("SEARCH", "RESPONSE FAILED");
            }
        });

        ConveniarAPI.closeClient();
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        SearchParam = "";
    }

}
