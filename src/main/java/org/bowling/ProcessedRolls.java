package org.bowling;

import java.util.Set;

public class ProcessedRolls {

    public ProcessedRolls(Set<Roll> matchedRolls, int score) {
        this.score = score;
        this.matchedRolls = matchedRolls;
    }

    private int score;
    private Set<Roll> matchedRolls;

    public int getScore() {
        return score;
    }

    public Set<Roll> getMatchedRolls() {
        return matchedRolls;
    }
}