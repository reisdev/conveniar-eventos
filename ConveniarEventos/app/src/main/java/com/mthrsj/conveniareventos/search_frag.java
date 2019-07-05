package com.mthrsj.conveniareventos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
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
    SearchView search;
    private static String[] EventosSearchBar;


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
        return v;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState){

        search = getActivity().findViewById(R.id.textinput);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchParam = query;
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                getEvents();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        search.setQuery(SearchParam,true);
        EditText editText = ( search.findViewById(androidx.appcompat.R.id.search_src_text));
        editText.setHintTextColor(getResources().getColor(R.color.black));
        editText.setTextColor(getResources().getColor(R.color.black));
        eventList = new ArrayList<>();

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getEvents();
        updateEventsList();

        ImageButton goBack = getActivity().findViewById(R.id.voltar_inicial);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicial_frag frag = new inicial_frag();
                FragmentManager fm = getFragmentManager();

                //fm.beginTransaction().replace(R.id.frame_layout, frag.newInstance(fnd)).commit();
            }
        });

    }

    public void updateEventsList(){
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);

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
                    try {
                        Event e = new Event(response.body().get(0));
                        if(e.getCodEvent() == -1) {
                            eventList.clear();
                        }
                        else {
                            eventList = new ArrayList<>();
                            eventList.addAll(response.body());
                        }
                        updateEventsList();
                    }
                    catch (NullPointerException e){
                        Log.d("SEARCH", "Error: " + e.toString());
                    }
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
    public void onDestroy(){
        super.onDestroy();
        SearchParam = "";
    }

}
