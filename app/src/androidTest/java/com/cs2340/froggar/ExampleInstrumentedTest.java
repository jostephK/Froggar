package com.cs2340.froggar;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void playerMoveRightWaterNotBounded() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.right();
        assertEquals(4, a.getCol());
    }
    @Test
    public void playerMoveLeftWaterNotBounded() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.left();
        assertEquals(4, a.getCol());
    }
    @Test
    public void playerMoveLeft() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.left();
        a.left();
        a.left();
        a.left();
        a.left();
        a.left();
        a.left();
        a.left();
        a.left();
        assertEquals(0, a.getCol());
    }

    @Test
    public void playerMoveRightBoundedNew() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.right();
        a.right();
        a.right();
        a.right();
        a.right();
        a.right();
        a.right();
        a.right();
        a.right();
        assertEquals(8, a.getCol());
    }

    @Test
    public void playerMoveUpWater() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        assertEquals(5, a.getRow());
    }
    @Test
    public void playerMoveUpWaterColRecalculation() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        assertEquals(0, a.getCol());
    }
    @Test
    public void playerMoveDownWaterColRecalculation() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.down();
        assertEquals(0, a.getCol());
    }
    @Test
    public void playerMissWinTileScore() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSprite a = new PlayerSprite(appContext, 1, 1, 1);
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        a.up();
        assertEquals(0, a.score());
    }
}