package com.cs2340.froggar;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class TouchInput implements View.OnTouchListener {
    private GestureDetector gestDetect;
    public TouchInput(Context game) {
        gestDetect = new GestureDetector(game, new GestureListener());
    }
    public boolean onTouch(View v, MotionEvent g) {
        return gestDetect.onTouchEvent(g);
    }
    public void right() {
    }
    public void left() {
    }
    public void up() {
    }
    public void down() {
    }
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            up();
            return true;
        }
        @Override
        public boolean onFling(MotionEvent d, MotionEvent m, float vX, float vY) {
            try {
                float dX = m.getX() - d.getX();
                float dY = m.getY() - d.getY();
                if (Math.abs(dX) > Math.abs(dY)) {
                    if (Math.abs(dX) > 100 && Math.abs(vX) > 100) {
                        if (dX > 0) {
                            right();
                        } else {
                            left();
                        }
                    }
                } else {
                    if (Math.abs(dY) > 100 && Math.abs(vY) > 100) {
                        if (dY > 0) {
                            down();
                        } else {
                            up();
                        }
                    }
                }
            } catch (Exception exception) {
                //Shrug
            }
            return false;
        }
        @Override
        public boolean onDown(MotionEvent g) {
            return true;
        }
    }
}