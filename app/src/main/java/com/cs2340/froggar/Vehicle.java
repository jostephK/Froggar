package com.cs2340.froggar;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Vehicle {
    protected ImageView vehicle;
    protected PlayerSprite sprite;

    protected long speed;
    protected int start;
    protected int end;
    protected int row;

    public Vehicle(Context current, ConstraintLayout top, int vehicle, int row, char direction,
                   long speed, PlayerSprite sprite) {
        this.vehicle = new ImageView(current);
        this.vehicle.setImageResource(vehicle);
        top.addView(this.vehicle);
        this.row = row;

        DisplayMetrics specs = current.getResources().getDisplayMetrics();

        int dispHeight = specs.heightPixels;
        int dispWidth = specs.widthPixels;


        this.vehicle.getLayoutParams().height = dispHeight / 13;
        this.vehicle.getLayoutParams().width = dispHeight / 9;

        if (vehicle == R.drawable.sportscar) {
            this.vehicle.setY(dispHeight / 13 * row + (dispHeight / 13 / 4));
            this.vehicle.getLayoutParams().height /= 2;
            this.vehicle.getLayoutParams().width /= 2;
        } else {
            this.vehicle.setY(dispHeight / 13 * row);
        }

        if (direction == 'l') {
            this.start = dispWidth;
            this.end = 0 - dispHeight / 9;
        } else {
            this.start = 0 - dispHeight / 9;
            this.end = dispWidth;
        }

        this.vehicle.setX(start);

        this.speed = speed;
        this.sprite = sprite;
    }

    public void animate() {
        Handler timer = new Handler();
        Runnable animator = new Runnable() {
            @Override
            public void run() {
                vehicle.setX(vehicle.getX() + speed);
                Rect vehicleRect = new Rect();
                vehicle.getHitRect(vehicleRect);
                if (Rect.intersects(vehicleRect, sprite.getRect())) {
                    sprite.reset();
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