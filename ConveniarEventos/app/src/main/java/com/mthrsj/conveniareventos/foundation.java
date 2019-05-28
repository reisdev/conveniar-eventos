package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class foundation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        Intent it = getIntent();
        String[] aux = it.getStringArrayExtra("foundation_bundle");
        FoundationPicker FndPicker = findViewById(R.id.FoundationList);
        FndPicker.setValues(aux);
    }
}
