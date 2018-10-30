package org.bowling;

public class Application {
    private ScoringSystem scoringSystem;

    public Application() {
        scoringSystem = new ScoringSystem();
    }

    public void bowl(String gameRolls) {
        int frameNum = 1;
        char[] rolls = gameRolls.toCharArray();
        for (int i=1; i < rolls.length+1; i++) {
            scoringSystem.insertFact(new Roll(rolls[i-1]+"", frameNum, i));
            if(i % 2 == 0 || isStrike(rolls[i-1])) scoringSystem.scoreFrame(); // score each frame or last frame?
            frameNum++;
        }
    }

    public int getScore() {
        return scoringSystem.getTotal();
    }

    public boolean isStrike(char roll) {
        return roll == 'X';
    }
}