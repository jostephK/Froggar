package com.cs2340.froggar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigScreen extends AppCompatActivity {
    private int pos = 1;
    private int spriteSelector = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        //Fullscreen UI
        View decorView = getWindow().getDecorView();
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        //Player config
        EditText player = findViewById(R.id.inputName);
        String[] difficulty = {"Easy", "Normal", "Hard"};
        Button prevDiff = findViewById(R.id.pDiff);
        Button nextDiff = findViewById(R.id.nDiff);
        TextView diffName = findViewById(R.id.diffText);
        int[] sprite = {R.drawable.ducky, R.drawable.frog, R.drawable.slime, R.drawable.walkboi,
            R.drawable.blob, R.drawable.friend};
        Button pSprite = findViewById(R.id.prevSprite);
        Button nSprite = findViewById(R.id.nextSprite);
        ImageView spriteIMG = findViewById(R.id.imageSprite);
        nextDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos++;
                if (pos == difficulty.length) {
                    pos = 0;
                }
                diffName.setText(difficulty[pos]);
            }
        });
        prevDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos--;
                if (pos < 0) {
                    pos = difficulty.length - 1;
                }
                diffName.setText(difficulty[pos]);
            }
        });
        nSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelector++;
                if (spriteSelector == sprite.length) {
                    spriteSelector = 0;
                }
                spriteIMG.setImageResource(sprite[spriteSelector]);
            }
        });
        pSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelector--;
                if (spriteSelector < 0) {
                    spriteSelector = sprite.length - 1;
                }
                spriteIMG.setImageResource(sprite[spriteSelector]);
            }
        });
        Button play = findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View conf) {
                if (isValidName(player.getText().toString())) {
                    Intent confIntent = new Intent(getApplicationContext(), GameScreen.class);
                    confIntent.putExtra("namePlayer", player.getText().toString());
                    confIntent.putExtra("difficulty", difficulty[pos]);
                    confIntent.putExtra("spriteSelected", sprite[spriteSelector]);
                    startActivity(confIntent);
                    finish();
                } else if (player.getText().toString().length() > 16) {
                    Toast.makeText(getApplicationContext(), "Character Limit: 16!",
                            Toast.LENGTH_SHORT).show();
                } else if (player.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name cannot be blank!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        if (name.trim().isEmpty()) {
            return false;
        }
        if (name.length() > 16) {
            return false;
        }
        return true;
    }
}