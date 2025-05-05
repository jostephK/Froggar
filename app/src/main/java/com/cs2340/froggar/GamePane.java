package com.cs2340.froggar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import androidx.constraintlayout.widget.ConstraintLayout;


public class GamePane extends SurfaceView implements Runnable {
    //SurfaceView variables
    private Thread trd;
    private boolean trdCheck = true;
    private SurfaceHolder surfHold;
    // Display metrics passed in.
    private DisplayMetrics disp;
    private int width;
    private int height;
    // Tiles used for map.
    private int[] tiles = {R.drawable.board64,
        R.drawable.forest64,
        R.drawable.stream64,
        R.drawable.river64,
        R.drawable.sand64,
        R.drawable.road64,
        R.drawable.sidewalk64};
    //Map: [col][row] 9 x 13 tiles.
    private int[][] map = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 2, 1, 2, 1, 2, 1, 2, 1},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {4, 4, 4, 4, 4, 4, 4, 4, 4},
        {5, 5, 5, 5, 5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5, 5, 5, 5, 5},
        {6, 6, 6, 6, 6, 6, 6, 6, 6}};
    //Vehicle sprites
    private int[] vehicles = {R.drawable.smalltruck,
        R.drawable.foodtruck,
        R.drawable.saucetruck,
        R.drawable.sportscar};
    //Water Obstacle sprites
    private int[] waterObstacles = {R.drawable.log, R.drawable.lilypad};
    //Layout
    private ConstraintLayout mainLayout;
    //Animator
    private AnimatorManager animator;
    public GamePane(Context c) {
        super(c);
        this.surfHold = getHolder();
        this.disp = c.getResources().getDisplayMetrics();
        this.width = disp.widthPixels;
        this.height = disp.heightPixels;
    }
    public GamePane(Context c, ConstraintLayout layout, int dispWidth, int dispHeight,
                    PlayerSprite selectedSprite) {
        super(c);
        this.surfHold = getHolder();
        this.width = dispWidth;
        this.height = dispHeight;
        this.mainLayout = layout;
        this.animator = new AnimatorManager(c, mainLayout, selectedSprite);
    }
    @Override
    protected void onMeasure(int measureWidth, int measureHeight) {
        setMeasuredDimension(width, height);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.animate();
    }
    @Override
    public void run() {
        while (trdCheck) {
            draw();
        }
    }
    public void pause() {
        trdCheck = false;
        try {
            trd.join();
        } catch (InterruptedException e) {
            //Shrug
        }
    }
    public void resume() {
        trdCheck = true;
        trd = new Thread(this);
        trd.start();
    }
    protected void draw() {
        if (surfHold.getSurface().isValid()) {
            Canvas canvas = surfHold.lockCanvas();
            // Draw Tilemap
            for (int col = 0; col < map.length; col++) {
                for (int row = 0; row < map[0].length; row++) {
                    switch (map[col][row]) {
                    case 0:
                        canvas.drawBitmap(
                                Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[0]), width / 9, height / 13,
                                        false),
                                row * (width / 9), col * (height / 13), null);
                        break;
                    case 1:
                        canvas.drawBitmap(
                               Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[1]), width / 9, height / 13,
                                        false),
                                row * (width / 9), col * (height / 13), null);
                        break;
                    case 2:
                        canvas.drawBitmap(
                                Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[2]), width / 9, height / 13,
                                        false),
                                row * (width / 9), col * (height / 13), null);
                        break;
                    case 3:
                        canvas.drawBitmap(
                                 Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[3]), width / 9, height / 13,
                                        false),
                                row * (width / 9), col * (height / 13), null);
                        break;
                    case 4:
                        canvas.drawBitmap(
                                Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[4]), width, height / 13, false),
                                row * (width / 9), col * (height / 13), null);
                        break;
                    case 5:
                        canvas.drawBitmap(
                                Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[5]), width, height / 13, false),
                                row * width / 9, col * (height / 13), null);
                        break;
                    case 6:
                        canvas.drawBitmap(
                                Bitmap.createScaledBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                        tiles[6]), width, height / 13, false),
                                row * width / 9, col * (height / 13), null);
                        break;
                    default:
                        break;
                    }
                }
            }
            surfHold.unlockCanvasAndPost(canvas);
        }
    }

    public static int[][] getMap() {
        return (new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 2, 1, 2, 1, 2, 1, 2, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6, 6, 6, 6}});
    }
}
