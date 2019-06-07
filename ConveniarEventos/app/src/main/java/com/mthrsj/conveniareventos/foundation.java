package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.API.models.URLS;

import java.util.ArrayList;

public class foundation extends AppCompatActivity {

    String[] aux;
    ArrayList<URLS> domain;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        it = getIntent();
        aux = it.getStringArrayExtra("foundation_name");
        domain = (ArrayList<URLS>) it.getSerializableExtra("foundation_domain");
        Log.d("DEB",domain.toString());
        final FoundationPicker FndPicker = findViewById(R.id.FoundationList);
        FndPicker.setValues(aux);

        FndPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                it = new Intent(foundation.this, MainActivity.class);
                Foundation f = new Foundation(aux[position], domain.get(position));
                it.putExtra("foundation",f);
                startActivity(it);
            }
        });
    }
}
