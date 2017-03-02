package me.kawaijoe.rwbinary.controller.helper;

import javafx.application.Platform;
import me.kawaijoe.rwbinary.controller.EndGameScreenController;

public class EndGameScreenHelper implements Runnable {

    EndGameScreenController endGameScreenController;

    public EndGameScreenHelper(EndGameScreenController endGameScreenController) {
        this.endGameScreenController = endGameScreenController;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            endGameScreenController.getCorrectQuestion().setText(endGameScreenController.getStringCorrectQuestion());
            endGameScreenController.getTimeTaken().setText(endGameScreenController.getStringTimeTaken());
        });
    }
}
