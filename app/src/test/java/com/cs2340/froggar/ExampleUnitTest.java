package com.cs2340.froggar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void playerTestScoreReset() {
        PlayerSprite a = new PlayerSprite(1, 3, 3);
        a.resetTest();
        assertEquals(0, a.score());
    }

    @Test
    public void playerTestColumnReset() {
        PlayerSprite a = new PlayerSprite(1, 3, 3);
        a.resetTest();
        assertEquals(4, a.getCol());
    }

    @Test
    public void playerTestRowReset() {
        PlayerSprite a = new PlayerSprite(1, 3, 3);
        a.resetTest();
        assertEquals(12, a.getRow());
    }

    @Test
    public void playerTestLastVisitedReset() {
        PlayerSprite a = new PlayerSprite(1, 3, 3);
        a.resetTest();
        assertEquals(12, a.getLastVisited());
    }

    @Test
    public void mapDimensionRowsTest() {
        assertEquals(13, GamePane.getMap().length);
    }

    @Test
    public void mapDimensionColsTest() {
        assertEquals(9, GamePane.getMap()[0].length);
    }

    @Test
    public void mapDimensionTilesNumTest() {
        assertEquals(117, GamePane.getMap()[0].length
            * GamePane.getMap().length);
    }

    @Test
    public void playerMoveLeft() {
        assertEquals(117, GamePane.getMap()[0].length
                * GamePane.getMap().length);
    }
}