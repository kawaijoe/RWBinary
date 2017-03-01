package me.kawaijoe.rwbinary.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameScreenController {

    private boolean hasLogin;

    @FXML
    private Label timeField;
    @FXML
    private TextField answerField;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        submitButton.setDisable(true);
    }

    @FXML
    private void onSubmit() {

    }

    @FXML
    private void onAnswerField() {
        submitButton.setDisable(answerField.getText().trim().equals(""));
    }

    @FXML
    private void onHelp() {
        // TODO: Yet to implement pop up box
    }

    public void setHasLogin(boolean hasLogin) {
        this.hasLogin = hasLogin;
    }
}
