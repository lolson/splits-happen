package org.bowling;

import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

public class ScoringSystem {
    NameValueReferableMap<Roll> rolls;

    private int total;

    public ScoringSystem() {
        rolls = new FactMap<>();
    }

    public void insertFact(Roll roll) {
        rolls.put(roll.getKey(), new Fact<>(roll));
    }

    public void scoreFrame() {
        boolean notAllScored = true;
        while(notAllScored) {
            processRules();
            if(rolls.isEmpty()) notAllScored = false;
        }
        //System.out.println("After scoring frame "+getTotal());
    }

    private void processRules() {
        RuleBookRunner ruleBook = new RuleBookRunner("org.bowling.rules");
        ruleBook.run(rolls);
        ruleBook.getResult().ifPresent(result -> addToTotal(result));
    }

    private void addToTotal(Result result) {
        if(result.getValue() instanceof ProcessedRolls) {
            for (Roll roll : ((ProcessedRolls) result.getValue()).getMatchedRolls()) {
                rolls.remove(roll.getKey());
            }
            total += ((ProcessedRolls) result.getValue()).getScore();
        }
    }

    public int factsSize() {
        return rolls.size();
    }

    public int getTotal() {
        return total;
    }
}