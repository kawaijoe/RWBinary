package me.kawaijoe.rwbinary.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.kawaijoe.rwbinary.controller.helper.StopWatchThread;
import me.kawaijoe.rwbinary.image.ImageManager;
import me.kawaijoe.rwbinary.utility.Utility;
import me.kawaijoe.rwbinary.word.WordManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameScreenController {

    private String answer;
    private int currentQuestion = 0;
    private int noCorrect = 0;
    private ImageManager imageManager;
    private WordManager wordManager;
    private StopWatchThread stopWatchThread;
    // Change this values to ask different kinds of questions
    private List<Integer> questionLength = new ArrayList<>(Arrays.asList(
            5,6,7
    ));

    @FXML
    private Label timeField;
    @FXML
    private TextField answerField;
    @FXML
    private Button submitButton;
    @FXML
    private VBox cardVBox;

    @FXML
    private void initialize() {
        submitButton.setDisable(true);
        imageManager = ImageManager.getUniqueInstance();
        wordManager = WordManager.getUniqueInstance();

        stopWatchThread = new StopWatchThread(timeField);
        new Thread(stopWatchThread).start();

        getNewQuestion();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        if(answerField.getText().trim().equals(answer)) {
            noCorrect++;
            if(questionLength.size() > currentQuestion) {
                getNewQuestion();
                answerField.setText("");
            } else {
                gameOver(event);
            }
        } else {
            getNewQuestion();
        }
    }

    private void gameOver(ActionEvent event) throws IOException {
        System.out.println("GAME OVER");
        long millis = stopWatchThread.getCurrentTime();
        System.out.println("Time = " +  millis);
        stopWatchThread.stop();

        String correctQuestion = noCorrect + " / " + questionLength.size();
        String timeTaken = String.format("%d : %d : %d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                TimeUnit.MILLISECONDS.toMillis(millis) -
                        TimeUnit.MILLISECONDS.toMillis(TimeUnit.MINUTES.toSeconds(millis))

        );

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/endgamescreen.fxml"));

        Parent root = fxmlLoader.load();
        EndGameScreenController controller = fxmlLoader.getController();
        controller.setStringCorrectQuestion(correctQuestion);
        controller.setStringTimeTaken(timeTaken);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    private void getNewQuestion() {
        currentQuestion++;
        clearScreen();
        answer = wordManager.getRandomWord(questionLength.get(currentQuestion - 1));
        System.out.println(answer);
        displayNewQuestion(Utility.textToBinary(answer));
    }

    private void displayNewQuestion(String binary) {
        List<HBox> hBoxes = new ArrayList<>();
        HBox hBox;

        for(String oneWordBinary : binary.split(" ")) {
            hBox = new HBox();
            for(String individualBinary : oneWordBinary.split("")) {
                ImageView imageView;
                if(individualBinary.equals("1")) {
                    imageView = new ImageView(imageManager.getRandomCard());
                } else {
                    imageView = new ImageView(imageManager.getCardBack());
                }

                hBox.getChildren().add(imageView);
            }

            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            hBoxes.add(hBox);
        }

        for(HBox box : hBoxes) {
            cardVBox.getChildren().add(box);
        }

        cardVBox.isFillWidth();
    }

    private void clearScreen() {
        cardVBox.getChildren().clear();
    }

    @FXML
    private void onAnswerField() {
        submitButton.setDisable(answerField.getText().trim().equals(""));
    }

    @FXML
    private void onHelp() {
        // TODO: Yet to implement pop up box
    }
}
