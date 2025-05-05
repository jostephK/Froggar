package com.cs2340.froggar;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen UI
        View decorView = getWindow().getDecorView();
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(WelcomeScreen.this, StartScreen.class);
                startActivity(welcomeIntent);
                finish();
            }
        }, 2000);
    }
}