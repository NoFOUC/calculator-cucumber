package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for loading and launching the GUI calculator layout
 */
public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/gui/calculator.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.setMinHeight(700);
        stage.setMinWidth(740);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
