package com.mthrsj.conveniareventos;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicial_frag extends Fragment {

    RecyclerView recyclerView;
    EventAdapter adapter;
    List<Event> eventList;

    public static inicial_frag newInstance() {
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

        //ADICIONANDO ITENS PARA O EVENT LIST (MANUALMENTE PARA VISUALIZACAO)
        eventList.add(
                new Event(
                        1,
                        "Aula Violão",
                        "Ativo",
                        "100",
                        "Evento",
                        "Curso de violão basico para iniciantes"
                )
        );
        adapter = new EventAdapter(this.getContext(), eventList);
        recyclerView.setAdapter(adapter);
    }
}
