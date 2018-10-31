# Bowling Application Usage

The contents of this repo is a Java code implementation for the Bowling Score development challenge as defined in the read me document.  Unit tests demonstrate the four validation
test cases specified in the development challenge requirements and all assert the
correct total scores for the given inputs.  The following guide describes building and running the application and discusses some design considerations:

## Dependencies
The one required dependency for building this Bowling Score application is Java.
- JRE 1.8 or higher

Gradle is an optional dependency to build and run this project.
- Gradle 4.9 or higher (optional)

## Build and Test
To build and test this project, from the project root run:

    gradle build

To build and test this project without a Gradle install, from the project root run with the gradle wrapper:

    ./gradlew build

Otherwise on a Windows OS run:

     gradlew.bat build

## Running
Generate the program executable.

    gradle install

Navigate to `splits-happen/build/install/splits-happen/bin`

Run the executable with valid bowling game input.

    ./splits-happen X7/9-X-88/-6XXX81

Which will show the calculated score in the console.   

    You scored 167

Alternatively, this application can be run with Gradle, for instance:

    gradle run --args="9-9-9-9-9-9-9-9-9-9-"

Which will show the calculated score in the console.    

    > Task :run
    You scored 90


Or run with the gradle wrapper:

    ./gradlew run --args="9-9-9-9-9-9-9-9-9-9-"

## Validation Tests
From the project root run:

    gradle test | grep Score

And note the successful outcomes.

    Score: 150, given input: 5/5/5/5/5/5/5/5/5/5/5
    Score: 167, given input: X7/9-X-88/-6XXX81
    Score: 300, given input: XXXXXXXXXXXX
    Score: 90, given input: 9-9-9-9-9-9-9-9-9-9-

## Unit Test Report
Open build/reports/tests/test/index.html with a browser to view the success rate of all the executed unit tests.

# Application Design

The main application ingests a string of characters that describes a completed game of bowling.  It iterates over the input string and based on the rules of play determines sets of bowls that should be scored together.  A scoring system class is called to process sets of rolls and calculates a running total score.  After the main application finds the tenth and last set of bowls to be scored it returns the final score found by the scoring system.

## Data Flows

Data flows from main application where it is extracted as sets of bowl roll values. Each scorable set of bowl roll values is inserted in into the "working memory" of the rule engine where each is processed and scored according the rules that define the scoring for all possible bowl rolls try values. A scoring system class which manages the rule engine keeps track of the accumulated game score.

## Design Approach

The intent of using Rulebook rule engine is to allow for a separation of concerns between the main logic which defines the rules of play and the scoring logic.  Rather than expressing all scoring conditional logic in the form of coupled if-then-else statements, simple annotated rule classes in the form of a given-when-then grammars define decoupled scoring logic.  The chain of rules implement the Chain of Responsibility design pattern where in each rule attempts to handle scoring of roll set input independently of any other rule along the chain
of rules.  Rules are applied to facts when the rule engine is run.  

In the case of this application, rules define grammars in the form of: Given a set of rolls, when the roll type matches a possible input pattern, then iterate over the matching set
and increment the score for that type of roll.  The sets of rolls are the facts acted on by the defined rules. Each roll fact is inserted into the rule engine as main application as it iterates over the program input. After a set of rolls is matched and its score is calculates, it is removed from the map of working facts so that is not evaluated again. Scoring of a turn is complete when all rolls have been evaluated and removed from map of available facts.      

# Use of FOSS

The build stage uses Delivered Technologies rulebook-core as a compile time dependency.  This lighweight rule engine is licensed under a permissive Apache License.  It's only condition for software redistribution is the inclusion of the Apache License for clear attribution of the software.
