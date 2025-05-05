package com.cs2340.froggar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    private GamePane gameView;
    private PlayerSprite sprite;
    private int lives;
    private int multiplier;
    private int score = 0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //Fullscreen UI
        View decorView = getWindow().getDecorView();
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());

        //Display Metrics
        DisplayMetrics disp = getResources().getDisplayMetrics();
        int dispWidth = disp.widthPixels;
        int dispHeight = disp.heightPixels;

        //Difficulty string output
        TextView oDifficulty = findViewById(R.id.difficultyText);
        String difficulty = "Difficulty: " + getIntent().getExtras().getString("difficulty");
        oDifficulty.setText(difficulty);

        //Life counter based on difficulty selection
        TextView lifeText = findViewById(R.id.livesText);
        String lifeOuput = "Lives: ";
        if (difficulty.equals("Difficulty: Normal")) {
            lives = 3;
            lifeOuput = lifeOuput + lives;
            lifeText.setText(lifeOuput);
            multiplier = 2;
        } else if (difficulty.equals("Difficulty: Hard")) {
            lives = 1;
            lifeOuput = lifeOuput + lives;
            lifeText.setText(lifeOuput);
            multiplier = 3;
        } else {
            lives = 6;
            lifeOuput = lifeOuput + lives;
            lifeText.setText(lifeOuput);
            multiplier = 1;
        }

        //Scoreboard
        TextView scoreText = findViewById(R.id.scoreText);

        //GameView
        ConstraintLayout mainLayout;
        mainLayout = findViewById(R.id.mainContainer);
        sprite = new PlayerSprite(GameScreen.this, mainLayout,
                getIntent().getIntExtra("spriteSelected", R.drawable.frog),
                lives, multiplier, scoreText, lifeText);
        gameView = new GamePane(this, mainLayout, dispWidth, dispHeight, sprite);
        mainLayout.addView(gameView, 0);

        //Player Name output
        TextView oName = findViewById(R.id.outputName);
        String name = getIntent().getExtras().getString("namePlayer");
        oName.setText(name);

        //View Touch Listener
        gameView.setOnTouchListener(new TouchInput(this) {
            private int col = 4;
            private int row = 12;
            private int lastVisited = 12;

            @Override
            public void right() {
                sprite.right();
            }
            @Override
            public void left() {
                sprite.left();
            }
            @Override
            public void up() {
                sprite.up();
            }
            @Override
            public void down() {
                sprite.down();
            }
        });
    }
    public static int difficultySetter(String difficulty) {
        if (difficulty.equals("Difficulty: Normal")) {
            return 2;
        } else if (difficulty.equals("Difficulty: Hard")) {
            return 3;
        } else {
            return 1;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
    }
}