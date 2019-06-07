package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.API.models.URLS;

import java.util.List;

public class foundation extends AppCompatActivity {

    String[] aux;
    List<URLS> domain;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        it = getIntent();
        aux = it.getStringArrayExtra("foundation_name");
        domain = (List<URLS>) it.getSerializableExtra("foundation_domain");
        final FoundationPicker FndPicker = findViewById(R.id.FoundationList);
        FndPicker.setValues(aux);

        FndPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                it = new Intent(foundation.this, MainActivity.class);
                Bundle bd = new Bundle();
                Foundation f = new Foundation(aux[position], domain.get(position));
                bd.putSerializable("foundation", f);
                it.putExtras(bd);
                startActivity(it);
            }
        });
    }
}
