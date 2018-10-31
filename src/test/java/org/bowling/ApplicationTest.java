package org.bowling;

import org.junit.Before;
import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
public class ApplicationTest {
//    private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);
    private Application app;

    @Before
    public void setup() {
        app = new Application();
    }

    @Test
    public void score300() {
        String in = "XXXXXXXXXXXX";
        app.bowl(in);
        System.out.println("Score: "+app.getScore()+", given input: "+in);
        assertTrue(app.getScore() == 300);
    }

    @Test
    public void score90() {
        String in = "9-9-9-9-9-9-9-9-9-9-";
        app.bowl(in);
        System.out.println("Score: "+app.getScore()+", given input: "+in);
        assertTrue(app.getScore() == 90);
    }

    @Test
    public void score150() {
        String in = "5/5/5/5/5/5/5/5/5/5/5";
        app.bowl(in);
        System.out.println("Score: "+app.getScore()+", given input: "+in);
        assertTrue(app.getScore() == 150);
    }

    @Test
    public void score167() {
        String in = "X7/9-X-88/-6XXX81";
        app.bowl(in);
        System.out.println("Score: "+app.getScore()+", given input: "+in);
        assertTrue(app.getScore() == 167);
    }

    @Test
    public void score48() {
        app.bowl("X7/9-");
//        System.out.println("score: "+app.getScore());
        assertTrue(app.getScore() == 48);
    }

    @Test
    public void score74() {
        app.bowl("X7/9-X-8");
//        System.out.println("score: "+app.getScore());
        assertTrue(app.getScore() == 74);
    }

    @Test
    public void score90SoFar() {
        app.bowl("X7/9-X-88/-6");
//        System.out.println("score: "+app.getScore());
        assertTrue(app.getScore() == 90);
    }

    @Test
    public void score14() {
        app.bowl("X11");
//        System.out.println("score: "+app.getScore());
        assertTrue(app.getScore() == 14);
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