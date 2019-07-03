package com.mthrsj.conveniareventos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mthrsj.conveniareventos.Utils.Session;

public class perfil_frag extends Fragment {
    private String[] options = new String[] {"Meus Dados","Alterar Senha", "Ajuda", "Sair"};
    Session session;

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

        session = new Session(this.getContext());
        final ArrayAdapter options_adapter = new ArrayAdapter(inflated.getContext(),R.layout.menu_item);
        options_adapter.clear();
        options_adapter.addAll(options);
        ListView options_list  = inflated.findViewById(R.id.menu_list);
        options_list.setAdapter(options_adapter);

        options_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        session.logOut();

                        login search = new login();
                        FragmentManager fm = getFragmentManager();

                        fm.beginTransaction().replace(R.id.frame_layout, login.newInstance()).commit();
                        break;
                }
            }
        });

        return inflated;
    }
}
