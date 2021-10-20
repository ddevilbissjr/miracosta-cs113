package HW2_GithubIntegration;

import HW2_GithubIntegration.model.AssistantJack;
import HW2_GithubIntegration.model.Theory;

import java.util.Random;
import java.util.Scanner;

public class DetectiveJill {
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder = 1, weapon = 1, location = 1;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);

        do {
            answer = new Theory(weapon,location,murder);
            solution = jack.checkAnswer(answer);

            if(solution == 1) { // incorrect weapon
                weapon++;
            }
            if(solution == 2) { // incorrect location
                location++;
            }
            if(solution == 3) { // incorrect murder
                murder++;
            }
        } while(solution != 0);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }
    }
}
