package com.mthrsj.conveniareventos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class perfil_frag extends Fragment {
    private String[] options = new String[] {"Meus Dados","Alterar Senha", "Ajuda", "Sair"};

    public static perfil_frag newInstance() {
        perfil_frag fragment = new perfil_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.fragment_perfil_frag, container, false);
        ArrayAdapter options_adapter = new ArrayAdapter(inflated.getContext(),R.layout.menu_item);
        options_adapter.clear();
        options_adapter.addAll(options);
        ListView options_list  = inflated.findViewById(R.id.menu_list);
        options_list.setAdapter(options_adapter);
        return inflated;
    }
}
