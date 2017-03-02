package me.kawaijoe.rwbinary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class StartScreenController {

    @FXML
    private TextField groupNumberField;

    @FXML
    private TextField passwordField;

    @FXML
    private void initialize() {
        // Do nothing
    }

    @FXML
    private void onPlay(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gamescreen.fxml"));

        Parent root = fxmlLoader.load();
        GameScreenController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
