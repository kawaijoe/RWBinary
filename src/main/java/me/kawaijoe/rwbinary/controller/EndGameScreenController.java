package me.kawaijoe.rwbinary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.kawaijoe.rwbinary.controller.helper.EndGameScreenHelper;

import java.io.IOException;

public class EndGameScreenController {

    private String stringCorrectQuestion;
    private String stringTimeTaken;

    @FXML
    private Label correctQuestion;
    @FXML
    private Label timeTaken;

    @FXML
    private void initialize() {
        // Cheeky workaround
        EndGameScreenHelper endGameScreenHelper = new EndGameScreenHelper(this);
        new Thread(endGameScreenHelper).start();
    }

    @FXML
    private void onTryAgain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/startscreen.fxml"));

        Parent root = fxmlLoader.load();
        StartScreenController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public String getStringCorrectQuestion() {
        return stringCorrectQuestion;
    }

    public void setStringCorrectQuestion(String stringCorrectQuestion) {
        this.stringCorrectQuestion = stringCorrectQuestion;
    }

    public String getStringTimeTaken() {
        return stringTimeTaken;
    }

    public void setStringTimeTaken(String stringTimeTaken) {
        this.stringTimeTaken = stringTimeTaken;
    }

    public Label getCorrectQuestion() {
        return correctQuestion;
    }

    public void setCorrectQuestion(Label correctQuestion) {
        this.correctQuestion = correctQuestion;
    }

    public Label getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Label timeTaken) {
        this.timeTaken = timeTaken;
    }
}
