import java.util.Random;
public class Question {
    /**Instance variables**/
    public String question;
    private String answer;
    Random random = new Random();


    /**Getters and Setters**/
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }
    public Question(int min, int max, char[] operations) {
        /**Declare variables to store random numbers and selected operation**/
        int RandomMaxNumber;
        int RandomMinNumber;
        int RandomOperation = random.nextInt(operations.length);
        /**If max is negative, generate random negative numbers**/
        if(max < 0) {
            /**Convert min and max to strings for manipulation**/
            String max1 = String.valueOf(max);
            String min1 = String.valueOf(min);
            /**Generate random numbers based on the substring manipulation**/
            int tempRandomMaxNumber = random.nextInt(Integer.parseInt(min1.substring(1, min1.length() -1) + max1.substring(1, max1.length() -1)));
            int tempRandomMinNumber = random.nextInt(Integer.parseInt(min1.substring(1, min1.length() -1) + max1.substring(1, max1.length() -1)));
            /**Construct negative numbers from the generated random numbers**/
            String tempmax = ("-" + tempRandomMaxNumber);
            String tempmin = ("-" + tempRandomMinNumber);
            RandomMaxNumber = Integer.parseInt(tempmax);
            RandomMinNumber = Integer.parseInt(tempmin);
        } else {
            RandomMaxNumber = random.nextInt(max - min) + min;
            RandomMinNumber = random.nextInt(max - min) + min;
        }
        /**Rearranging min and max incase min is greater than max so that RandomMinNumber is less than or equal to RandomMaxNumber**/
        if (RandomMinNumber > RandomMaxNumber) {
            int RandomTempNumber = RandomMaxNumber;
            RandomMaxNumber = RandomMinNumber;
            RandomMinNumber = RandomTempNumber;
        }
        /**Create the question and answer based on the operation**/
        switch (operations[RandomOperation]) {
            case '%':
                question = RandomMaxNumber + " % " + RandomMinNumber + " = ";
                int divintanswer = RandomMaxNumber / RandomMinNumber;
                int modulointanswer = RandomMaxNumber % RandomMinNumber;
                answer = divintanswer + "r" + modulointanswer;
                break;
            case '+':
                question = RandomMaxNumber + " + " + RandomMinNumber + " = ";
                answer = String.valueOf(RandomMaxNumber + RandomMinNumber);
                break;
            case '-':
                question = RandomMaxNumber + " - " + RandomMinNumber + " = ";
                answer = String.valueOf(RandomMaxNumber - RandomMinNumber);
                break;
            case '*':
                question = RandomMaxNumber + " * " + RandomMinNumber + " = ";
                answer = String.valueOf(RandomMaxNumber * RandomMinNumber);
                break;
            case '/':
                question = RandomMaxNumber + " / " + RandomMinNumber + " = ";
                /**Use Double variable so that Math.round is able to round to nearest Int**/
                    answer = String.valueOf(Math.round((double) RandomMaxNumber /  (double) RandomMinNumber));
                    break;
                }
    }
    /**Override to return the full question and answer instead of reference point**/
    @Override
    public String toString() {
        return getQuestion();
    }
}
