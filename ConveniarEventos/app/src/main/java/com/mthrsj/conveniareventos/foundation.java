package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.mthrsj.conveniareventos.models.Category;
import com.mthrsj.conveniareventos.models.Foundation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class foundation extends AppCompatActivity {

    String[] aux;
    String[] domain;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        it = getIntent();
        aux = it.getStringArrayExtra("foundation_name");
        domain = it.getStringArrayExtra("foundation_domain");
        final FoundationPicker FndPicker = findViewById(R.id.FoundationList);
        FndPicker.setValues(aux);

        FndPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                it = new Intent(foundation.this, MainActivity.class);
                Bundle bd = new Bundle();
                Foundation f = new Foundation(aux[position], domain[position]);
                bd.putSerializable("foundation", f);
                it.putExtras(bd);
                Log.d("FND", aux[position] + ": " + domain[position]);
                startActivity(it);
            }
        });
    }
}
