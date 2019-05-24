package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        getFoundationsList();
    }

    public String getFoundationsList() {
        RequestQueue queue = Volley.newRequestQueue(foundation.this);
        String url = "https://servicos.conveniar.com.br/adminservico/api/fundacoes";
        final NumberPicker FoundationsList = (NumberPicker) findViewById(R.id.foundation_picker);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("REQ", response.toString());
                String [] foundations = new String[response.length()];
                FoundationsList.setMinValue(0);
                FoundationsList.setMaxValue(response.length()-1);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject fnd = response.getJSONObject(i);
                        String[] parts = fnd.get("Name").toString().split(" ");
                        foundations[i] = parts[0];
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                FoundationsList.setDisplayedValues(foundations);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERR", error.getLocalizedMessage());
            }
        });
        queue.add(request);
        return "";
    }
}
