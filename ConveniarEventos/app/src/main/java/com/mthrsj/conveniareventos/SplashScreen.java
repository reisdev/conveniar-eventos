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

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

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

        new Handler().postDelayed(new Runnable() {
            /*
            * Exibindo splash com timer
            */
            @Override
            public void run() {
                Intent it = new Intent(SplashScreen.this, foundation.class);
                startActivity(it);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
