package com.cs2340.froggar;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Log extends Vehicle {
    public Log(Context current, ConstraintLayout top, int vehicle, int row, char direction,
               long speed, PlayerSprite sprite) {
        super(current, top, vehicle, row, direction, speed, sprite);
    }

    @Override
    public void animate() {
        Handler timer = new Handler();
        Runnable animator = new Runnable() {
            @Override
            public void run() {
                vehicle.setX(vehicle.getX() + speed);
                Rect vehicleRect = new Rect();
                vehicle.getHitRect(vehicleRect);
                if (sprite.getRow() == row) {
                    if (Rect.intersects(vehicleRect, sprite.getRect())) {
                        sprite.getSprite().setX(sprite.getSprite().getX() + speed);
                        if (sprite.getSprite().getX() <= 0 - sprite.getDispWidth() / 9
                            || sprite.getSprite().getX() >= sprite.getDispWidth()) {
                            sprite.reset();
                        }
                    } else {
                        sprite.reset();
                    }
                }
                if (end < 0) {
                    if (vehicle.getX() <= end) {
                        vehicle.setX(start);
                    }
                } else {
                    if (vehicle.getX() >= end) {
                        vehicle.setX(start);
                    }
                }
                timer.postDelayed(this, 10);
            }
        };
        timer.postDelayed(animator, 1000);
    }
}
