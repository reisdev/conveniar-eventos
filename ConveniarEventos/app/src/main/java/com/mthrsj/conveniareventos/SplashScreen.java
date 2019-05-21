package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            /*
            * Exibindo splash com timer
            */
            @Override
            public void run() {
                Intent it = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(it);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
