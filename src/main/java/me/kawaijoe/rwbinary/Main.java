package me.kawaijoe.rwbinary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.kawaijoe.rwbinary.image.ImageManager;
import me.kawaijoe.rwbinary.word.WordManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        bootstrapper();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startscreen.fxml"));
        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        primaryStage.setTitle("Rogue Wars Binary Game");
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }

    private void bootstrapper() {
        WordManager.getUniqueInstance().readWords("words.txt");
        ImageManager.getUniqueInstance().readImages("image/modern/", "card_back_large.png",
                new ArrayList<>(Arrays.asList("card_back_alt_large.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
