import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class Main extends Application {

    int currentQuestion = 0;
    int score = 0;

    String[] questions = {
            "What is 2 + 2?",
            "Capital of India?",
            "5 * 3 + 6 / 3 = ?"
    };

    String[][] options = {
            {"3", "4", "5", "6"},
            {"Mumbai", "Ahmedabad", "Kolkata", "New Delhi"},
            {"10", "15", "18", "25"}
    };

    int[] answers = {1, 3, 2}; // correct option index
    

    Label questionLabel = new Label();
    Button[] optionButtons = new Button[4];
    Label resultLabel = new Label("");
    Button nextButton = new Button("Next");
    Button restartButton = new Button("Restart");

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Create option buttons
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new Button();
            optionButtons[i].setMinWidth(200);
            int index = i;

            optionButtons[i].setOnAction(e -> checkAnswer(index));
        }
        

        nextButton.setOnAction(e -> nextQuestion());
        restartButton.setOnAction(e -> restartQuiz());
        restartButton.setVisible(false);

        root.getChildren().add(questionLabel);
        root.getChildren().addAll(optionButtons);
        root.getChildren().addAll(resultLabel, nextButton);
        root.getChildren().add(restartButton);

        loadQuestion();

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Quiz App");
        stage.show();
    }

    void loadQuestion() {
        questionLabel.setText(questions[currentQuestion]);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestion][i]);
            optionButtons[i].setDisable(false);
            optionButtons[i].setStyle("");
        }

        resultLabel.setText("");
        nextButton.setDisable(false);
    }


    void checkAnswer(int selected) {
        
    int correctIndex = answers[currentQuestion];
        for (Button btn : optionButtons) {
        btn.setDisable(true);   
        }
        if (selected == answers[currentQuestion]) {
            resultLabel.setText("Correct!");
            score++;
            optionButtons[selected].setStyle("-fx-background-color: green;");
        } else {
            resultLabel.setText("Wrong!");
            optionButtons[selected].setStyle("-fx-background-color: red;");
            optionButtons[correctIndex].setStyle("-fx-background-color: green;");
        }
        

    }

    void nextQuestion() {
        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            showResult();
        }
    }

    void showResult() {
        questionLabel.setText("Quiz Finished!");
        resultLabel.setText("Score: " + score);

        for (Button btn : optionButtons) {
            btn.setDisable(true);
        }

        nextButton.setDisable(true);
        restartButton.setVisible(true);
    }

    void restartQuiz() {
        currentQuestion = 0;
        score = 0;

        nextButton.setDisable(false);
        restartButton.setVisible(false);

        loadQuestion();
    }
    public static void main(String[] args) {
        launch();
    }
}