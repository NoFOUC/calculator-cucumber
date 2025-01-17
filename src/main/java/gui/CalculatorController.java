package gui;

import calculator.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import parser.Parser;

import java.util.ArrayList;

/**
 * Main controller for the GUI application
 */
public class CalculatorController {

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
    private Button buttonBiggerThan;

    @FXML
    private Button buttonComma;

    @FXML
    private Button buttonComplexModulus;

    @FXML
    private Button buttonCycleRepresentations;

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
    private Button buttonFactorial;

    @FXML
    private Button buttonImaginary;

    @FXML
    private Button buttonLesserThan;

    @FXML
    private Button buttonMinus;

    @FXML
    private Button buttonModulo;

    @FXML
    private Button buttonMoveRight;

//    @FXML
//    private Button buttonParenthesisClose;

    @FXML
    private Button buttonParenthesisOpen;

    @FXML
    private Button buttonPlus;

    @FXML
    private Button buttonPrecisionDown;

    @FXML
    private Button buttonPrecisionUp;

    @FXML
    private Button buttonSqrt;

    @FXML
    private Button buttonTimes;

    @FXML
    private Button buttonToDeg;

    @FXML
    private Button buttonToRad;

    @FXML
    private Label equalsLabel;

    @FXML
    private Label equationLabel;

    @FXML
    private FlowPane paneExtras;

    @FXML
    private FlowPane paneNumbers;

    @FXML
    private FlowPane paneOperations;

    @FXML
    private Label precisionValueLabel;

    @FXML
    private Button buttonAcos;

    @FXML
    private Button buttonAcotg;

    @FXML
    private Button buttonAsin;

    @FXML
    private Button buttonAtan;

    @FXML
    private Button buttonCos;

    @FXML
    private Button buttonCotg;

    @FXML
    private Button buttonInverse;

    @FXML
    private Button buttonSin;

    @FXML
    private Button buttonTan;

    @FXML
    private Button buttonLog;

    @FXML
    private Button buttonRng;


    // Other variables

    private EquationDisplay equationRoot;
    private EquationDisplay cursor;

    private int precision;
    public final int precisionBoundary = 32; // TODO Decide what's the max precision : j'ai mis 10 dans le toString

    private MyNumber result;

    private DisplayType displayType = DisplayType.CARTESIAN;

    @FXML
    private void initialize() {
        equationRoot = new EquationDisplay();
        cursor = equationRoot;
        precision = 6;
        setupButtons();
    }

    /**
     * Method for initializing the button actions of the GUI
     */
    private void setupButtons() {

        button0.setOnAction((event) -> addNumber(0));
        button1.setOnAction((event) -> addNumber(1));
        button2.setOnAction((event) -> addNumber(2));
        button3.setOnAction((event) -> addNumber(3));
        button4.setOnAction((event) -> addNumber(4));
        button5.setOnAction((event) -> addNumber(5));
        button6.setOnAction((event) -> addNumber(6));
        button7.setOnAction((event) -> addNumber(7));
        button8.setOnAction((event) -> addNumber(8));
        button9.setOnAction((event) -> addNumber(9));

        buttonComma.setOnAction((event) -> addOperation("."));

        buttonPlus.setOnAction((event) -> addOperation("+"));
        buttonMinus.setOnAction((event) -> addOperation("-"));

        buttonTimes.setOnAction((event) -> addOperation("*"));
        buttonDivide.setOnAction((event) -> addOperation("/"));

        buttonExp.setOnAction((event) -> addOperation("exp", "(", ")"));
        buttonSqrt.setOnAction((event) -> addOperation("√", "(", ")"));

        buttonParenthesisOpen.setOnAction((event) -> addOperation("(", ")"));

        buttonImaginary.setOnAction((event) -> addOperation("i"));
        buttonFactorial.setOnAction((event) -> addOperation("!"));
        buttonModulo.setOnAction((event) -> addOperation("%"));
        buttonComplexModulus.setOnAction((event) -> addOperation("||", "", "||"));

        buttonLesserThan.setOnAction((event) -> addOperation("<"));
        buttonBiggerThan.setOnAction((event) -> addOperation(">"));

        buttonToDeg.setOnAction((event) -> addOperation("DEG", "(",")"));
        buttonToRad.setOnAction((event) -> addOperation("RAD", "(",")"));

        buttonSin.setOnAction((event) -> addOperation("sin", "(",")"));
        buttonAsin.setOnAction((event) -> addOperation("asin", "(",")"));

        buttonCos.setOnAction((event) -> addOperation("cos", "(",")"));
        buttonAcos.setOnAction((event) -> addOperation("acos", "(",")"));

        buttonTan.setOnAction((event) -> addOperation("tan", "(",")"));
        buttonAtan.setOnAction((event) -> addOperation("atan", "(",")"));

        buttonCotg.setOnAction((event) -> addOperation("cotg", "(",")"));
        buttonAcotg.setOnAction((event) -> addOperation("acotg", "(",")"));

        buttonLog.setOnAction((event) -> addOperation("ln", "(",")"));
        buttonInverse.setOnAction((event) -> {addNumber(1); addOperation("/");});

        buttonRng.setOnAction((event) -> addOperation("RNG", "(",")"));

        buttonMoveRight.setOnAction((event) -> {
            cursor = cursor.moveCursorRight();
            refreshEquationDisplay();
        });
        buttonErase.setOnAction((event) -> {
            cursor = cursor.erase();
            refreshEquationDisplay();
        });
        buttonEraseAll.setOnAction((event) -> handleEraseAll());

        buttonPrecisionUp.setOnAction((e) -> setPrecision(precision+1));
        buttonPrecisionDown.setOnAction((e) -> setPrecision(precision-1));

        buttonCycleRepresentations.setOnAction((e) -> reprRotation());

        buttonEquals.setOnAction((e) -> calculate());

    }

    /**
     * Method for evaluating the inputted expression
     */
    private void calculate() {

        ArrayList<Object> processed = equationRoot.toArrayList();

        try {
            if (processed.size() > 0) {
                result = Parser.main(processed);
                equalsLabel.setText("= "+result.toString(displayType));

                equationRoot.reset();
                cursor = equationRoot;
            }
        }
        catch (ArithmeticException e) {
            equalsLabel.setText("Division by zero");
        }
        catch (IllegalConstruction e) {
            equalsLabel.setText("Illegal construction");
        }
        catch (IllegalArgumentException e) {
            equalsLabel.setText(e.getMessage());
        }

    }

    /**
     * Modifies the powers of ten breakpoint of the scientific notation display
     * @param value
     */
    private void setPrecision(int value) {
        if (0 < value && value <= precisionBoundary) {
            precision = value;
            AbstractValue.setGlobalContractionLimit(precision);
            precisionValueLabel.setText(""+precision);
        }
    }

    private void addNumber(int number) {
        EquationDisplay term = new EquationDisplay(number);
        cursor.add(term);
        cursor = term;
        refreshEquationDisplay();
    }

    private void addOperation(String repr) {
        EquationDisplay term = new EquationDisplay(repr);
        cursor.add(term);
        cursor = term;
        refreshEquationDisplay();
    }

    private void addOperation(String open, String close) {
        EquationDisplay term = new EquationDisplay(open, close);
        cursor.add(term);
        cursor = term;
        refreshEquationDisplay();
    }

    private void addOperation(String ope, String open, String close) {
        EquationDisplay term = new EquationDisplay(ope, open, close);
        cursor.add(term);
        cursor = term;
        refreshEquationDisplay();
    }

    private void refreshEquationDisplay() {
        equationLabel.setText(equationRoot.toString());
    }

    private void updateEqualsDisplay() {
        if (result != null) equalsLabel.setText(result.toString(displayType));
        else equalsLabel.setText("");
    }

    /**
     * Rotates between the three modes of displaying complex numbers in the result field
     */
    private void reprRotation() {
        switch (displayType) {
            case CARTESIAN -> {
                displayType = DisplayType.EXPONENTIAL;
                buttonCycleRepresentations.setText("⟳ | Exp.");
            }
            case EXPONENTIAL -> {
                displayType = DisplayType.POLAR;
                buttonCycleRepresentations.setText("⟳ | Polar");
            }
            default -> {
                displayType = DisplayType.CARTESIAN;
                buttonCycleRepresentations.setText("⟳ | Cart.");
            }
        }
        updateEqualsDisplay();
    }

    private void handleEraseAll() {
        equationRoot.reset();
        cursor = equationRoot;
        refreshEquationDisplay();
    }

}
