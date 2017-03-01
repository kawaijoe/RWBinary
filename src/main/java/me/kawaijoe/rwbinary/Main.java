package me.kawaijoe.rwbinary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.kawaijoe.rwbinary.word.WordManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        bootstrapper();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startscreen.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        //scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        primaryStage.setTitle("Rogue Wars Binary Game");
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void bootstrapper() {
        WordManager.getUniqueInstance("words.txt");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
