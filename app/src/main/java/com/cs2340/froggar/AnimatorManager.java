package com.cs2340.froggar;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;


public class AnimatorManager {
    private ConstraintLayout mainlayout;
    private Context current;

    private PlayerSprite sprite;

    public AnimatorManager(Context current, ConstraintLayout mainlayout, PlayerSprite sprite) {
        this.current = current;
        this.mainlayout = mainlayout;
        this.sprite = sprite;
    }

    public void animate() {
        new Log(current, mainlayout, R.drawable.smalllog, 6,
                'l', -5, sprite).animate();
        new Log(current, mainlayout, R.drawable.log, 5,
                'r', 7, sprite).animate();
        new Log(current, mainlayout, R.drawable.smalllog, 4,
                'l', -4, sprite).animate();
        new Log(current, mainlayout, R.drawable.log, 3,
                'r', 3, sprite).animate();
        new Log(current, mainlayout, R.drawable.smalllog, 2,
                'l', -6, sprite).animate();
        animateFoodtruck();
        animateSaucetruck();
        animateSportscar();
        animateSmalltruck();

        sprite.getSprite().bringToFront();
    }

    public void animateFoodtruck() {
        new Vehicle(current, mainlayout, R.drawable.foodtruck, 10,
                'l', -15, sprite).animate();
    }

    public void animateSportscar() {
        new Vehicle(current, mainlayout, R.drawable.sportscar, 8,
                'l', -20, sprite).animate();
    }

    public void animateSaucetruck() {
        new Vehicle(current, mainlayout, R.drawable.saucetruck, 9,
                'r', 10, sprite).animate();
    }

    public void animateSmalltruck() {
        new Vehicle(current, mainlayout, R.drawable.smalltruck, 11,
                'r', 5, sprite).animate();
    }
}
