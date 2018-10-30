package org.bowling.rules;

import com.deliveredtechnologies.rulebook.annotation.*;
import org.bowling.ProcessedRolls;
import org.bowling.Roll;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Rule(order = 2)
public class MissRule {

    private int score = 0;

    @Given
    List<Roll> rolls;

    @Result
    private ProcessedRolls processedRolls;

    @When
    public boolean when() {
        return rolls.stream()
                .anyMatch(roll -> roll.getValue().matches("-") );
    }

    @Then
    public void then() {
        System.out.println("Miss Rule, - found");
        Set<Roll> set = rolls.stream()
                .filter(roll -> roll.getValue().matches("-"))
                .collect(Collectors.toSet());
        processedRolls = new ProcessedRolls(set, score);
    }

}