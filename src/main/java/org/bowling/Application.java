package org.bowling;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Application {
//    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private ScoringSystem scoringSystem;

    public Application() {
        scoringSystem = new ScoringSystem();
    }

    public void bowl(String gameRolls) {
        int frameNum = 0;
        char[] rolls = gameRolls.toCharArray();
        for (int i=0; i < rolls.length; i++) {
            // strike score includes next 2 throws
            if(isStrike(rolls[i])) {
                scoringSystem.insertFact(new Roll(rolls[i] + "", i));
                scoringSystem.insertFact(new Roll(rolls[i+1] + "", i + 1));
                if(isSpare(rolls[i+2])) {
                    int prevPins = Integer.parseInt(rolls[i+1]+"");
                    int sparePins = 10 - prevPins;
                    scoringSystem.insertFact(new Roll(sparePins + "", i + 2));
                } else {
                    scoringSystem.insertFact(new Roll(rolls[i+2] + "", i + 2));
                }
                scoringSystem.scoreFrame();
                frameNum++;
            // spare score includes spare value, previous and next throws
            } else if (isSpare(rolls[i])) {
                // spare is 10 - previous roll
                int prevPins = Integer.parseInt(rolls[i-1]+"");
                int sparePins = 10 - prevPins;
                // insert spare value, previous and next
                scoringSystem.insertFact(new Roll(sparePins+"", i+1));
                scoringSystem.insertFact(new Roll(rolls[i+1]+"",i+2));
                scoringSystem.scoreFrame();
                frameNum++;
            } else {
                scoringSystem.insertFact(new Roll(rolls[i] + "", i));
                // Each frame gets 2 attempts
                if (scoringSystem.factsSize() > 1) {
                    if (frameNum == 10 && (isStrike(rolls[i]) || isSpare(rolls[i]))) { // insert any bonus rolls
                        for (; i < rolls.length + 1; i++) {
                            scoringSystem.insertFact(new Roll(rolls[i] + "", i));
                        }
                    }
                    scoringSystem.scoreFrame(); // score each frame or last frame?
                    frameNum++;
                }
            }
            // Game over after 10 frames
            if(frameNum > 9) break;
        }
    }

    public int getScore() {
        return scoringSystem.getTotal();
    }

    public boolean isStrike(char roll) {
        return roll == 'X';
    }

    public boolean isSpare(char roll) {
        return roll == '/';
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.bowl(args[0]);
        System.out.println("You scored "+app.getScore());
    }
}