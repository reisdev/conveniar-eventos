package com.mthrsj.conveniareventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.Database.Database;
import com.mthrsj.conveniareventos.Utils.Database.Models.Config;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Foundation foundation;
    int actual_frag = 1;
    Boolean new_frag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        Bundle bd = it.getExtras();
        foundation = new Foundation((Foundation) bd.getSerializable("foundation"));

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        nav.setItemIconSize(180);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag = null;
                switch (item.getItemId()) {
                    case R.id.frag_inicial:
                        if(actual_frag != 1) {
                            selectedFrag = inicial_frag.newInstance(foundation);
                            new_frag = true;
                        } else new_frag = false;
                        actual_frag = 1;
                        break;
                    case R.id.frag_fav:
                        if(actual_frag != 2) {
                            selectedFrag = favorito_frag.newInstance();
                            new_frag = true;
                        } else new_frag = false;
                        actual_frag = 2;
                        break;
                    case R.id.frag_ins:
                        if(actual_frag != 3) {
                            selectedFrag = insc_frag.newInstance();
                            new_frag = true;
                        } else new_frag = false;
                        actual_frag = 3;
                        break;
                    case R.id.frag_perfil:
                        if(actual_frag != 4){
                            selectedFrag = perfil_frag.newInstance();
                            new_frag = true;
                        } else new_frag = false;
                        actual_frag = 4;
                        break;
                }
                if(new_frag == true){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFrag);
                    transaction.commit();
                }
                return true;
            }
        });
        //Define a tela inicial como padrão para mostrar no fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        inicial_frag frag = new inicial_frag();
        Bundle extras = new Bundle();
        extras.putSerializable("foundation", foundation);
        frag.setArguments(extras);
        transaction.replace(R.id.frame_layout, frag.newInstance(foundation));
        transaction.commit();
        //testRealm(foundation);
    }

    public void testRealm(Foundation fnd) {
        Realm db = Database.getInstance();
        db.beginTransaction();
        Config actualFoundation = db.createObject(Config.class);
        actualFoundation.setName("foundation");
        actualFoundation.setValue(fnd.getURLS().getEventos());
        final Config managedConfig = db.copyFromRealm(actualFoundation);
        db.commitTransaction();
        Log.d("DB", "Created config for foundation");

        db.beginTransaction();
        boolean result = db.where(Config.class).equalTo("name", "foundation").findAll().deleteFirstFromRealm();
        db.commitTransaction();
        Log.d("DB", "Deleted config for foundation: " + result);
    }
}
