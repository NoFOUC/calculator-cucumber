package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonComma;

    @FXML
    private Button buttonDivide;

    @FXML
    private Button buttonEquals;

    @FXML
    private Button buttonErase;

    @FXML
    private Button buttonEraseAll;

    @FXML
    private Button buttonExp;

    @FXML
    private Button buttonMinus;

    @FXML
    private Button buttonParenthesisClose;

    @FXML
    private Button buttonParenthesisOpen;

    @FXML
    private Button buttonPlus;

    @FXML
    private Button buttonSqrt;

    @FXML
    private Button buttonTimes;

    @FXML
    private FlowPane paneExtras;

    @FXML
    private FlowPane paneNumbers;

    @FXML
    private FlowPane paneOperations;

    @FXML
    private Text screenLabelMain;

    @FXML
    private Text screenLabelSecondary;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.setMinHeight(700);
        stage.setMinWidth(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
