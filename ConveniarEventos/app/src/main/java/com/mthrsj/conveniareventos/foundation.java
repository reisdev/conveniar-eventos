package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.NumberPicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class foundation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        Intent it = getIntent();
        final NumberPicker FoundationsList = (NumberPicker) findViewById(R.id.foundation_picker);
        String[] foundations = it.getStringArrayExtra("foundation_bundle");
        FoundationsList.setMinValue(0);
        FoundationsList.setMaxValue(foundations.length-1);
        FoundationsList.setDisplayedValues(foundations);
    }



}
