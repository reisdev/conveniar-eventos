package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mthrsj.conveniareventos.models.Foundation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SplashScreen extends AppCompatActivity {

    private int progressStatus = 0;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pb = findViewById(R.id.progressBar);

        if (ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("PRM", "Permission not granted");
            /* Permission is not granted */
            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.INTERNET}, 1);
            progressStatus += 33;
            pb.setProgress(progressStatus);
        } else {
            Log.d("PRM", "Internet Permission granted");
        }

        if (isOnline()) {
            progressStatus += 33;
            pb.setProgress(progressStatus);
            getFoundationsList();
        } else {
            Log.e("NET", "Não esta connectado com a internet!");
            //DISPARAR ALERTA
        }
    }

    public void getFoundationsList() {
        ConveniarEndpoints apiService = ConveniarAPI.getClient().create(ConveniarEndpoints.class);
        Call<List<Foundation>> allFoundation = apiService.getFoundations();

        allFoundation.enqueue(new Callback<List<Foundation>>() {
            @Override
            public void onResponse(Call<List<Foundation>> call, retrofit2.Response<List<Foundation>> response) {
                if (response.isSuccessful()) {
                    Log.d("REQ","Request successfull");
                    goToFoundationView(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Foundation>> call, Throwable t) {
                    Log.e("REQ","Cannot get foundations list");
            }
        });

    }

    public void goToFoundationView(List<Foundation> response) {
        Log.i("GOTO", "Go to foundations");
        Intent it = new Intent(SplashScreen.this, foundation.class);
        String[] foundations = new String[response.size()];
        for (int i = 0; i < response.size(); i++) {
            foundations[i] = response.get(i).getName().split("\\s*([-]|[|])")[0];
        }
        it.putExtra("foundation_bundle", foundations);
        startActivity(it);
        finish();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = ((activeNetwork != null) && activeNetwork.isConnectedOrConnecting());

        return isConnected;
    }

}
