package com.cs2340.froggar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayerSprite {
    private ImageView sprite;
    private Context c;
    private int dispWidth;
    private int dispHeight;
    private int row;
    private int col;
    private int lastVisited;
    private final int[] scores = {150, 100, 40, 15, 35, 15, 20, 10, 30, 15, 20, 10};
    private int score;
    private int lives;
    private int multiplier;
    private TextView scoreText;
    private TextView lifeText;
    private boolean resets;
    public PlayerSprite(int sprite, int lives, int multiplier) {
        this.lives = lives;
        this.multiplier = multiplier;
    }
    public int getDispWidth() {
        return dispWidth;
    }

    public PlayerSprite(Context c, int sprite, int lives, int multiplier) {
        this.lives = lives;
        this.multiplier = multiplier;
        this.sprite = new ImageView(c);
        this.sprite.setX(4 * dispWidth / 9);
        this.sprite.setY(12 * (dispHeight / 13));
        row = 12;
        col = 4;
        score = 0;
        lastVisited = 0;
    }

    public PlayerSprite(Context c, ConstraintLayout mainLayout, int selectedSprite,
                        int lives, int multiplier, TextView scoreText, TextView lifeText) {
        DisplayMetrics specs = c.getResources().getDisplayMetrics();

        this.dispHeight = specs.heightPixels;
        this.dispWidth = specs.widthPixels;

        this.sprite = new ImageView(c);
        this.sprite.setImageResource(selectedSprite);
        mainLayout.addView(sprite);

        this.sprite.getLayoutParams().width = dispWidth / 9;
        this.sprite.getLayoutParams().height = dispHeight / 13;
        this.sprite.setX(4 * dispWidth / 9);
        this.sprite.setY(12 * (dispHeight / 13));

        row = 12;
        col = 4;
        lastVisited = 12;

        score = 0;
        this.c = c;
        this.lives = lives;
        this.multiplier = multiplier;
        this.scoreText = scoreText;
        this.lifeText = lifeText;
        resets = true;
    }
    public void win() {
        if (resets) {
            if (col % 2 == 0) {
                resets = false;
                Intent confIntent = new Intent((Activity) c, WinScreen.class);
                confIntent.putExtra("score", String.valueOf(score));
                c.startActivity(confIntent);
                ((Activity) (c)).finish();
            } else {
                reset();
            }
        }
    }
    public void reset() {
        if (resets) {
            if (lives == 0) {
                resets = false;
                Intent confIntent = new Intent((Activity) c, GameOverScreen.class);
                confIntent.putExtra("score", String.valueOf(score));
                c.startActivity(confIntent);
                ((Activity) (c)).finish();
            } else {
                row = 12;
                col = 4;
                score = 0;
                lastVisited = 12;
                lifeText.setText("Lives: " + --lives);
                String scoreString = "Score: " + score;
                scoreText.setText(scoreString);
                this.sprite.setX(4 * dispWidth / 9);
                this.sprite.setY(12 * (dispHeight / 13));
            }
        }
    }
    public void resetTest() {
        row = 12;
        col = 4;
        score = 0;
        lastVisited = 12;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getLastVisited() {
        return lastVisited;
    }
    public int score() {
        return score;
    }
    public int getMultiplier() {
        return multiplier;
    }
    public Rect getRect() {
        Rect playerRect = new Rect();
        sprite.getHitRect(playerRect);
        return playerRect;
    }
    public ImageView getSprite() {
        return sprite;
    }
    public void up() {
        if (row != 1) {
            if (row - 1 < lastVisited) {
                score += scores[row - 1] * multiplier;
                String scoreString = "Score: " + score;
                scoreText.setText(scoreString);
                lastVisited--;
            }
            if (row == 2) {
                col = (int) Math.rint(sprite.getX() / (dispWidth / 9));
                sprite.setX(Math.max(0, col * (dispWidth / 9)));
            }
            row--;
            if (row == 1) {
                win();
            }
            sprite.setY(Math.max(row * (dispHeight / 13), dispHeight / 13));
        }
    }
    public void down() {
        if (row != 12) {
            if (row == 6) {
                col = (int) Math.rint(sprite.getX() / (dispWidth / 9));
                sprite.setX(Math.max(0, col * (dispWidth / 9)));
            }
            row++;
            sprite.setY(Math.min(12 * (dispHeight / 13), row * (dispHeight / 13)));
        }
    }
    public void left() {
        if (row >= 2 && row <= 6) {
            sprite.setX(sprite.getX() - dispWidth / 9);
        } else if (col != 0) {
            col--;
            sprite.setX(Math.max(0, col * (dispWidth / 9)));
        }
    }
    public void right() {
        if (row >= 2 && row <= 6) {
            sprite.setX(sprite.getX() + dispWidth / 9);
        } else if (col != 8) {
            col++;
            sprite.setX(Math.min(col * (dispWidth / 9), 8 * (dispWidth / 9)));
        }
    }
}
