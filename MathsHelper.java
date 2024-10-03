import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.text.DecimalFormat;

public class MathsHelper {
    /** Instance Variables **/
    private int yearLevel;
    private int numQuestions;
    private String answer;
    public boolean confirmation = false;
    private QuestionGenerator quiz;
    private int min;
    private int max;
    private char[] operations;

    // IMPORTANT! This Scanner variable must remain public and non-final for code testing purposes
    public Scanner scan;
    DecimalFormat df = new DecimalFormat("#.##");

    public MathsHelper() {
        scan = new Scanner(System.in);
    }

    /**
     * Performs one session of the Maths quiz
     */
    public void letsPlay() {
        confirmation = false;
        displayWelcome();
        while (!confirmation) {
            displayYearMenu();
            yearLevel = scan.nextInt();
            scan.nextLine();
            displayQuestionMenu();
            numQuestions = scan.nextInt();
            scan.nextLine();
            confirmSessionDetails(yearLevel, numQuestions);
        }
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Let's begin ... (press 'Q' at any time to quit)");
        }
        quiz = new QuestionGenerator(yearLevel); // Initialize quiz with the correct year level
        quiz.generateQuestions(numQuestions * 10);
        askQuestions();
    }

    //-------------------------operational methods------------------------------

    /**
     * Defines the Welcome Message text
     */
    private void displayWelcome() {
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("|                      Welcome to the Maths Helper.                      |");
        System.out.println("|       Use this application to test your knowledge of mathematics.      |");
        System.out.println("|      This program is intended for children from reception to year 7    |");
        System.out.println("+------------------------------------------------------------------------+");
    }

    /**
     * Defines the Year Menu
     */
    private void displayYearMenu() {
        System.out.println("What is your year level? Choose an option from the list below:");
        System.out.println("+: addition, -: subtraction, *: multiplication, /: division, %: division with remainder");
        System.out.println("[0] Reception [+]");
        System.out.println("[1] Year 1 [+, -]");
        System.out.println("[2] Year 2 [+, -]");
        System.out.println("[3] Year 3 [+, -, *, /]");
        System.out.println("[4] Year 4 [+, -, *, /]");
        System.out.println("[5] Year 5 [+, -, *, /]");
        System.out.println("[6] Year 6 [+, -, *, /]");
        System.out.println("[7] Year 7 [+, -, *, /, %]");
    }

    /**
     * Defines the Question Menu
     */
    private void displayQuestionMenu() {
        System.out.println("How many questions would you like to attempt? Choose an option from the list below:");
        System.out.println("[1] 10 questions");
        System.out.println("[2] 20 questions");
        System.out.println("[3] 30 questions");
        System.out.println("[4] 40 questions");
        System.out.println("[5] 50 questions");
    }

    /** Getters and setters **/
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setQuiz(QuestionGenerator quiz) {
        this.quiz = quiz;
    }

    public QuestionGenerator getQuiz() {
        return quiz;
    }

    /**
     * Displays the confirmation message to the user and processes user input to
     * determine the accuracy of the information provided. If correct then
     * return true otherwise return false.
     *
     * @param year      the selected Year Menu item {0,1,2,3,4,5,6,7}
     * @param questions the selected Question Menu item {1,2,3,4,5}
     * @return boolean based on user's confirmation of correct data entry
     */
    public boolean confirmSessionDetails(int year, int questions) {
        if (year == 0) {
            System.out.print("You are a Reception student and want to do " + (questions * 10) + " questions. Is this correct (Y/N)?: ");
            answer = scan.nextLine();
        } else {
            System.out.print("You are a Year " + year + " student and want to do " + (questions * 10) + " questions. Is this correct (Y/N)?: ");
            answer = scan.nextLine();
        }
        if (answer.equalsIgnoreCase("Y")) {
            confirmation = true;
        } else {
            confirmation = false;
        }
        return confirmation;
    }

    /**
     * Asks the generated questions to the user and evaluates the responses
     */
    public void askQuestions() {
        String userAnswer;
        double correctAnswersCount = 0;
        double totalAnswersCount = quiz.getQuestions().size();
        int every_five_answer_checker = 0;
        /**Prints each question and intakes user input**/
        for (Question question : quiz.getQuestions()) {
            System.out.print(question);
            userAnswer = scan.nextLine();

            /**Scenario based on either correct/incorrect answer**/
            if (Objects.equals(question.getAnswer(), userAnswer)) {
                correctAnswersCount++;
                System.out.println("Correct! Well Done!");
            } else {
                System.out.println("Bad luck that was incorrect. The correct answer was " + question.getAnswer() + ".");
            }

            /**Calculates current percentage of correct answers compared to total answers**/
            double currentPercentage = (correctAnswersCount / (quiz.getQuestions().indexOf(question) + 1)) * 100;
            System.out.println("Your current percentage is " + df.format(currentPercentage) + "%");
            every_five_answer_checker++;
            if(every_five_answer_checker % 5 == 0 && currentPercentage > 75) {
                System.out.println("You are doing really well! Maybe try a harder difficulty.");
            } else if(every_five_answer_checker % 5 == 0 && currentPercentage < 30) {
                System.out.println("It seems you are having some trouble. Maybe try an easier difficulty.");
            }
        }
        /**Final Rating**/
        double totalPercentage = (correctAnswersCount / totalAnswersCount) * 100;
        System.out.println("Your total percentage was " + df.format(totalPercentage) + "%");

        if (totalPercentage < 40) {
            System.out.println("Bad luck. Try practicing with some lower year levels to build your confidence and skills.");
        } else if (totalPercentage >= 40 && totalPercentage < 50) {
            System.out.println("That was a good effort, but you may need to work on some expressions.");
        } else if (totalPercentage >= 50 && totalPercentage < 60) {
            System.out.println("Congratulations you passed. Keep practicing at this year level to improve your score.");
        } else if (totalPercentage >= 60 && totalPercentage < 75) {
            System.out.println("Well done. That was a good effort.");
        } else if (totalPercentage >= 75 && totalPercentage < 85) {
            System.out.println("Good job. You should try the next year level in your next test.");
        } else if (totalPercentage >= 85) {
            System.out.println("Excellent work! You really know your stuff. Try the harder levels next time.");
        }
    }
}




