package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.NumberPicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("PRM","Permission not granted");
            /* Permission is not granted */
            ActivityCompat.requestPermissions(SplashScreen.this,new String[] {Manifest.permission.INTERNET},1);
        }
        else {
            Log.d("PRM","Internet Permission granted");
        }

        getFoundationsList();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashScreen.this, foundation.class);
                startActivity(it);
                finish();
            }
        }, SPLASH_TIME_OUT);
        */
    }

    public void getFoundationsList() {
        RequestQueue queue = Volley.newRequestQueue(SplashScreen.this);
        String url = "https://servicos.conveniar.com.br/adminservico/api/fundacoes";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("REQ", response.toString());
                String[] foundations = new String[response.length()];
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject fnd = response.getJSONObject(i);
                        String[] parts = fnd.get("Name").toString().split(" ");
                        foundations[i] = parts[0];
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                goToFoundationView(foundations);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERR", error.getLocalizedMessage());
            }
        });
        queue.add(request);
    }

    public void goToFoundationView(String[] foundations){
        Intent it = new Intent(SplashScreen.this, foundation.class);
        it.putExtra("foundation_bundle", foundations);
        startActivity(it);
        finish();
    }
}
