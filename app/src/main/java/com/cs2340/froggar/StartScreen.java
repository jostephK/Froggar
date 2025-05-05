package com.cs2340.froggar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen UI
        View decorView = getWindow().getDecorView();
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        //Start button to ConfigScreen
        Button buttonStart;
        setContentView(R.layout.activity_start_screen);
        buttonStart = findViewById(R.id.restartButton);
        buttonStart.setOnClickListener(conf -> {
            Intent confIntent = new Intent(getApplicationContext(), ConfigScreen.class);
            startActivity(confIntent);
            finish();
        });
    }
}