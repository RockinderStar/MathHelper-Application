import java.util.ArrayList;

public class QuestionGenerator {
    /** Instance Variables: */
    private int min;
    private int max;
    private char[] operations;
    private ArrayList<Question> questions = new ArrayList<>();

    /** Constructor */
    public QuestionGenerator(int year) {
        this.min = findMin(year);
        this.max = findMax(year);
        this.operations = findOperations(year);
        this.questions = new ArrayList<>();
    }

    /** Getters and Setters */
    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setArrayList(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getArrayList() {
        return questions;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public void setOperations(char[] operations) {
        this.operations = operations;
    }

    public char[] getOperations() {
        return operations;
    }

    /**
     * Determines the minimum value based on the given year.
     *
     * @param year the year to determine the min value
     * @return the min value based on the year
     */
    public int findMin(int year) {
        if(year <= 4) {
            return min = 0;
        } else if(year > 4 && year <= 6) {
            return min = -999;
        } else {
            return min = -9999;
        }
    }

    /**
     * Determines the maximum value based on the given year.
     *
     * @param year the year to determine the max value
     * @return the max value based on the year
     */
    public int findMax(int year) {
        if(year <= 2) {
            return max = 9;
        } else if(year > 2 && year <= 4) {
            return max = 99;
        } else if (year > 4 && year <= 6) {
            return max = 999;
        } else {
            return max = 9999;
        }
    }

    /**
     * Determines the operations based on the given year.
     *
     * @param year the year to determine the operations
     * @return the operations based on the year
     */
    public char[] findOperations(int year) {
        if(year == 0) {
            operations = new char[1];
            operations[0] = '+';
            return operations;
        } else if(year > 0 && year <= 2) {
            operations = new char[2];
            operations[0] = '+';
            operations[1] = '-';
            return operations;
        } else if (year > 2 && year <= 6) {
            operations = new char[4];
            operations[0] = '+';
            operations[1] = '-';
            operations[2] = '*';
            operations[3] = '/';
            return operations;
        } else {
            operations = new char[5];
            operations[0] = '+';
            operations[1] = '-';
            operations[2] = '*';
            operations[3] = '/';
            operations[4] = '%';
            return operations;
        }
    }

    /**
     * Generates a specified number of questions and adds them to the list.
     *
     * @param num the number of questions to generate
     */
    public void generateQuestions(int num) {
        for(int i = 0; i < num; i++) {
            questions.add(new Question(min, max, operations));
            //System.out.println(questions.get(i));
        }
    }

    /**
     * Returns the list of generated questions.
     *
     * @return the list of questions
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
