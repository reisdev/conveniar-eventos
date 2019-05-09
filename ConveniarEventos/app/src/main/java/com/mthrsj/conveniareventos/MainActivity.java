package com.mthrsj.conveniareventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment selectedFrag = null;
                switch(item.getItemId()){
                    case R.id.frag_inicial:
                        selectedFrag = inicial_frag.newInstance();
                        break;
                    case R.id.frag_fav:
                        selectedFrag = favorito_frag.newInstance();
                        break;
                    case R.id.frag_ins:
                        selectedFrag = insc_frag.newInstance();
                        break;
                    case R.id.frag_perfil:
                        selectedFrag = perfil_frag.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFrag);
                transaction.commit();
                return true;
            }
        });

        //Define a tela inicial como padrão para mostrar no fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, inicial_frag.newInstance());
        transaction.commit();
    }
}
