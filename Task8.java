import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> quizQuestions = createQuizQuestions();
        int score = 0;

        System.out.println("--- Welcome to the Java Quiz! ---");
        System.out.println("Answer the following questions by entering the option number (e.g., 1, 2, 3, or 4).");
        System.out.println("---------------------------------");

        for (int i = 0; i < quizQuestions.size(); i++) {
            Question currentQuestion = quizQuestions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());

            // Display options
            List<String> options = currentQuestion.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            // Get user answer
            int userAnswer = -1;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Your answer: ");
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= options.size()) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and " + options.size() + ".");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Check answer and update score
            if (currentQuestion.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was option " + currentQuestion.getCorrectOptionIndex() + ".");
            }
        }

        System.out.println("\n--- Quiz Finished! ---");
        System.out.println("You scored " + score + " out of " + quizQuestions.size() + " questions.");
        System.out.println("----------------------");

        scanner.close();
    }

    // Helper method to create a list of quiz questions
    private static List<Question> createQuizQuestions() {
        List<Question> questions = new ArrayList<>();

        // Question 1
        questions.add(new Question(
            "What is the capital of France?",
            List.of("Berlin", "Madrid", "Paris", "Rome"),
            3 // Paris is at index 2, so option 3
        ));

        // Question 2
        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            List.of("Earth", "Mars", "Jupiter", "Venus"),
            2 // Mars is at index 1, so option 2
        ));

        // Question 3
        questions.add(new Question(
            "What is the largest ocean on Earth?",
            List.of("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            4 // Pacific Ocean is at index 3, so option 4
        ));

        // Question 4
        questions.add(new Question(
            "What is the chemical symbol for water?",
            List.of("O2", "H2O", "CO2", "N2"),
            2 // H2O is at index 1, so option 2
        ));

        return questions;
    }
}

/**
 * Represents a single quiz question with its text, options, and correct answer.
 */
class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex; // 1-based index for user display

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    /**
     * Checks if the user's provided answer (1-based index) is correct.
     * @param userAnswer The 1-based index of the user's chosen option.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOptionIndex;
    }

    /**
     * Returns the 1-based index of the correct option for display purposes.
     * @return The 1-based index of the correct option.
     */
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}