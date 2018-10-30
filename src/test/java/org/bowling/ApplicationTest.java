package org.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
public class ApplicationTest {
    private Application app;

    @Before
    public void setup() {
        app = new Application();
    }

    @Test
    public void scoreTwoMisses() {
        app.bowl("--");
        assertTrue(app.getScore() == 0);
    }

    @Test
    public void scoreOneMiss() {
        app.bowl("5-");
        assertTrue(app.getScore() == 5);
    }

    @Test
    public void scoreTwoFrames() {
        app.bowl("5431");
        assertTrue(app.getScore() == 13);
    }

    @Test
    public void scoreSingleFrame() {
        app.bowl("54");
        assertTrue(app.getScore() == 9);
    }
}