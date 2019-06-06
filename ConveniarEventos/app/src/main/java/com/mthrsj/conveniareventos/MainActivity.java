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
                        selectedFrag = inicial_frag.newInstance(foundation);
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
        inicial_frag frag = new inicial_frag();
        Bundle extras = new Bundle();
        extras.putSerializable("foundation", foundation);
        frag.setArguments(extras);
        transaction.replace(R.id.frame_layout, frag.newInstance(foundation));
        transaction.commit();
        testRealm(foundation);
    }

    public void testRealm(Foundation fnd) {
        Realm db = Database.getInstance();
        db.beginTransaction();
        Config actualFoundation = db.createObject(Config.class);
        actualFoundation.setName("foundation");
        actualFoundation.setValue(fnd.getDomain());
        final Config managedConfig = db.copyFromRealm(actualFoundation);
        db.commitTransaction();
        Log.d("DB", "Created config for foundation");

        db.beginTransaction();
        boolean result = db.where(Config.class).equalTo("name", "foundation").findAll().deleteFirstFromRealm();
        db.commitTransaction();
        Log.d("DB", "Deleted config for foundation: " + result);
    }
}
