package org.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
public class ScoringSystemTest {
    private ScoringSystem sys;

    @Before
    public void setup() {
        sys = new ScoringSystem();
    }

    @Test
    public void testBasicRule() {
        sys.insertFact(new Roll("9", 1));
        assertTrue(sys.factsSize() == 1);
        sys.scoreFrame();
        assertTrue(sys.getTotal() == 9);
    }

    @Test
    public void testBasicFrame() {
        sys.insertFact(new Roll("3", 1));
        sys.insertFact(new Roll("2", 2));
        assertTrue(sys.factsSize() == 2);
        sys.scoreFrame();
        assertTrue(sys.getTotal() == 5);
    }

    @Test
    public void testMissRule() {
        sys.insertFact(new Roll("-", 1));
        assertTrue(sys.factsSize() == 1);
        sys.scoreFrame();
        assertTrue(sys.getTotal() == 0);
    }

    @Test
    public void testMissedFrame() {
        sys.insertFact(new Roll("-", 1));
        sys.insertFact(new Roll("-", 2));
        assertTrue(sys.factsSize() == 2);
        sys.scoreFrame();
        assertTrue(sys.getTotal() == 0);
    }

    @Test
    public void testStrikeRule() {
        sys.insertFact(new Roll("X", 1));
        assertTrue(sys.factsSize() == 1);
        sys.scoreFrame();
        assertTrue(sys.getTotal() == 10);
    }
}