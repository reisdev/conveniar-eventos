package com.mthrsj.conveniareventos;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class Search extends AppCompatActivity {

    static String SearchParam;
    static Foundation fnd;
    private static String[] EventosSearchBar;
    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;
    SearchView search;
    private TextView.OnEditorActionListener searchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case IME_ACTION_SEARCH:
                    Log.d("SEARCH", "SEARCH UPDATE");
                    getEvents();
                    break;
            }
            return false;
        }
    };

    public static Search newInstance(final Foundation f, final String param) {
        SearchParam = param;
        fnd = f;
        return new Search();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle b = getIntent().getExtras();
        SearchParam = b.getString("search");
        search = findViewById(R.id.textinput);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchParam = query;
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow( g, 0);
                getEvents();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        search.setQuery(SearchParam, true);
        EditText editText = (search.findViewById(R.id.search_src_text));
        editText.setHintTextColor(getResources().getColor(R.color.black));
        editText.setTextColor(getResources().getColor(R.color.black));
        eventList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getEvents();
        updateEventsList();

        ImageButton goBack = findViewById(R.id.voltar_inicial);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //fm.beginTransaction().replace(R.id.frame_layout, frag.newInstance(fnd)).commit();
            }
        });
    }

    public void updateEventsList() {
        adapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(adapter);

        TextView textSearch = findViewById(R.id.text_search);
        TextView textSearchName = findViewById(R.id.text_search_name);
        int n = eventList.size();
        String txt = n + " resultados encontrados para";
        textSearch.setText(txt);
        textSearchName.setTypeface(Typeface.DEFAULT_BOLD);
        textSearchName.setText('"' + SearchParam + '"');
    }

    public void getEvents() {
        ConveniarEndpoints apiService = ConveniarAPI.getClient().create(ConveniarEndpoints.class);
        final Call<List<Event>> events = apiService.getEventsBySearchName(SearchParam);

        events.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()) {
                    Log.d("SEARCH", "RESPONSE IS SUCCESSFULL");
                    eventList = new ArrayList<>();
                    eventList.addAll(response.body());
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
    public void onDestroy() {
        super.onDestroy();
        SearchParam = "";
    }

}
