package org.bowling.rules;

import com.deliveredtechnologies.rulebook.annotation.*;
import org.bowling.ProcessedRolls;
import org.bowling.Roll;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Rule(order = 2)
public class StrikeRule {

    private int score = 0;

    @Given
    List<Roll> rolls;

    @Result
    private ProcessedRolls processedRolls;

    @When
    public boolean when() {
        return rolls.stream().anyMatch(roll -> roll.getValue().matches("X") );
    }

    @Then
    public void then() {
        System.out.println("Strike Rule, X found");
        Set<Roll> set = rolls.stream()
                .filter(roll -> roll.getValue().matches("X"))
                .collect(Collectors.toSet());
        for( Roll roll : set) {
            score += 10;
        }
        processedRolls = new ProcessedRolls(set, score);
    }

}