package com.mthrsj.conveniareventos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.Database.Database;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;

public class SplashScreen extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pb = findViewById(R.id.progressBar);
        pb.setMax(100);

        configureRealm();

        if (ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("PRM", "Permission not granted");
            /* Permission is not granted */
            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.INTERNET}, 1);
            for(int i =0; i < 3; i++) pb.incrementProgressBy(11);
        } else {
            Log.d("PRM", "Internet Permission granted");
            for(int i =0; i < 3; i++) pb.incrementProgressBy(11);
        }
        checkConnectivity();
    }

    @Override
    public void onResume(){
        super.onResume();
        checkConnectivity();
    }

    public void checkConnectivity() {
        if (isOnline()) {
            pb.incrementProgressBy(33);
            Log.d("CON", "Is online!");
            getFoundationsList();
        } else {
            Log.e("NET", "Não esta connectado com a internet!");
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
            builder.setMessage(R.string.noConnectionMessage).setTitle(R.string.noConnection);
            builder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    checkConnectivity();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void configureRealm() {
        Realm.init(this.getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().name("conveniar-eventos.realm").build();
        Database.configure(config);
        pb.incrementProgressBy(33);
    }


    public void getFoundationsList() {
        ConveniarEndpoints apiService = ConveniarAPI.getClient().create(ConveniarEndpoints.class);
        Call<List<Foundation>> allFoundation = apiService.getFoundations();

        allFoundation.enqueue(new Callback<List<Foundation>>() {
            @Override
            public void onResponse(Call<List<Foundation>> call, retrofit2.Response<List<Foundation>> response) {
                if (response.isSuccessful()) {
                    goToFoundationView(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Foundation>> call, Throwable t) {
                Log.e("REQ", "Cannot get foundations list");
            }
        });
        ConveniarAPI.closeClient();
    }

    public void goToFoundationView(List<Foundation> response) {
        Log.i("GOTO", "Go to foundations");
        Intent it = new Intent(SplashScreen.this, foundation.class);
        String[] foundations = new String[response.size()];
        String[] domain = new String[response.size()];
        for (int i = 0; i < response.size(); i++) {
            foundations[i] = response.get(i).getName().split("\\s*([-]|[|])")[0];
            domain[i] = response.get(i).getDomain();
        }
        it.putExtra("foundation_name", foundations);
        it.putExtra("foundation_domain", domain);
        startActivity(it);
        finish();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return ((activeNetwork != null) && activeNetwork.isConnectedOrConnecting());
    }
}
