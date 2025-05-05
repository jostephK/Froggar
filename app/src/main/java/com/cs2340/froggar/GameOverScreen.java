package com.cs2340.froggar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());

        setContentView(R.layout.game_over_screen);
        TextView score = findViewById(R.id.goscoreNum);
        score.setText(getIntent().getExtras().getString("score"));

        Button buttonExit = findViewById(R.id.exitButton);
        Button buttonRestart = findViewById(R.id.restartButton);

        buttonRestart.setOnClickListener(a -> {
            Intent confIntent = new Intent(getApplicationContext(), ConfigScreen.class);
            startActivity(confIntent);
            finish();
        });

        buttonExit.setOnClickListener(a -> {
            finish();
        });
    }
}
