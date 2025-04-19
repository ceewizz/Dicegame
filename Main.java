import java.util.Random;  
import java.util.Scanner;

public class Main {
     private enum Status {
    // Creating the status, Win, Lose, or undetermined
        WIN, LOSE, UNDETERMINED
    }

    

    private static int rollDice() {
        Random random = new Random();   // Create a random class to generate random numbers

        // Generates a random integer between 0 and 5 and then add 1 to shift range to 1 through 6
        int dice1 = random.nextInt(6) + 1; 
        int dice2 = random.nextInt(6) + 1;
        return dice1 + dice2;  // Calculates the sum of the two dice rolls and return in
    }

    private static int play() {
        Status playerStatus = Status.UNDETERMINED;
        int pointValue = 0;  // Initial point Value
        int rolls = 0;       // Initial rolls Value

        int rolledValue = rollDice();  // rolevalue dice roll, rolls is incremented
        rolls++;

        // If rolledValue is 2, 3 , or 12 the player immediately loses.
        if (rolledValue == 2 || rolledValue == 3 || rolledValue == 12) {
            playerStatus = Status.LOSE;

        // If rolledValue is 7 or 11 the player wins immediately
        } else if (rolledValue == 7 || rolledValue == 11) {
            playerStatus = Status.WIN;
        } else {

        // If rolled value is not 2, 3, 7, 11, 12 then the roll value becomes pointValue
            pointValue = rolledValue;

        // Within the loop player rolls dice repeatedly until player status is determined
            while (playerStatus == Status.UNDETERMINED) { 
                rolledValue = rollDice();
                rolls++;
        
        // If the player rolls a 7 after they established a point, they will loose
                if (rolledValue == 7) {
                    playerStatus = Status.LOSE;

        // If player rolls a pointValue again they win
                } else if (rolledValue == pointValue) {
                    playerStatus = Status.WIN;
                }
            }
        }

        
        return playerStatus == Status.WIN ? rolls : -rolls;
    }

    // Creating a scanner to ask for user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setting initial values to 0

        int wins = 0;
        int losses = 0;
        int totalWinRolls = 0;
        int totalLossRolls = 0;
        int gamesPlayed = 0;

        // Message asking how many times a user wants to play
        System.out.print("Please enter a number of times you want to play in digits only: ");
        
        // Take in user input with scanner.nextInt to only accept Integers input
        int timesToPlay = scanner.nextInt();

        // Initializing a loop condition
        while (gamesPlayed < timesToPlay) {
            gamesPlayed++;
        // The loop continues to execute as long as gamesPlayed is less than timesToPlay
           
        // Calling the play() function and its return value is stored in a variable rolls
            int rolls = play();
            if (rolls > 0) {    // If rolls is greater than 0, it indicates a win
                wins++;         // win is incremented by 1
                totalWinRolls += rolls;

        // Else losses is incremented by 1 
            } else {
                losses++;    
        // totalLossRolls is increased by the absolute value of rolls   
                totalLossRolls += -rolls;
            }
        }

        // Calculates the average number of rolls needed to achieve a win
        // If wins > 0 calculates the average by dividing totalWinRolls by wins
        // If there are no wins or wins is 0 set averageRollsPerWin to 0 to avoid division by 0
        double averageRollsPerWin = (wins > 0) ? (double) totalWinRolls / wins : 0;

        // Calculates the average number of rolls needed to result in a loss
        // If loss > 0 calculates the average by dividing totalLossRolls by losses
        // If there are no loss then loss is 0 set averageRollsPerLoss to 0
        double averageRollsPerLoss = (losses > 0) ? (double) totalLossRolls / losses : 0;
        
        // Calculates the winning percentage by dividing number of wins by total number of games played
        // Multiply by 100 to convert to percentage
        double winningPercentage = (double) wins / gamesPlayed * 100;

        // Message with number of wins
        System.out.println("Wins: " + wins);
        // Message with number of loss
        System.out.println("Losses: " + losses);
        // Message with Average rolls per win
        System.out.println("Average Rolls Per Win: " + averageRollsPerWin);
        //Message with Average rolls per loss
        System.out.println("Average Rolls Per Loss: " + averageRollsPerLoss);
        // Message with winning percentage
        System.out.println("Winning Percentage: " + winningPercentage + "%");
    }
}

