package org.bowling.rules;

import com.deliveredtechnologies.rulebook.annotation.*;
import org.bowling.ProcessedRolls;
import org.bowling.Roll;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Rule(order = 1)
public class BasicRule {

    private int score = 0;

    @Given
    List<Roll> rolls;

    @Result
    private ProcessedRolls processedRolls;

    @When
    public boolean when() {
        return rolls.stream().anyMatch(roll -> roll.getValue().matches("[1-9]") );
    }

    @Then
    public void then() {
        //System.out.println("Basic Rule, 1-9 found");
        Set<Roll> set = rolls.stream()
                .filter(roll -> roll.getValue().matches("[1-9]"))
                .collect(Collectors.toSet());
        for( Roll roll : set) {
            score += Integer.parseInt(roll.getValue());
        }
        processedRolls = new ProcessedRolls(set, score);
    }

}