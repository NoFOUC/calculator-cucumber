package gui;

import calculator.MyNumber;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./calculator.fxml"));
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
