package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class ClickableButtonsTest {

    @Start
    void onStart(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("./calculator.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.setMinHeight(700);
        stage.setMinWidth(740);
        stage.show();
    }


    @Test
    void should_contain_button_plus() {
        // expect:
        verifyThat("#buttonPlus", hasText("+"));
    }
    @Test
    void should_contain_button_minus() {
        // expect:
        verifyThat("#buttonMinus", hasText("-"));
    }

    @Test
    void should_contain_button_multiply() {
        // expect:
        verifyThat("#buttonTimes", hasText("*"));
    }

    @Test
    void should_contain_button_divide() {
        // expect:
        verifyThat("#buttonDivide", hasText("/"));
    }

    @Test
    void should_contain_button_exp() {
        // expect:
        verifyThat("#buttonExp", hasText("^"));
    }

    @Test
    void should_contain_button_sqrt() {
        // expect:
        verifyThat("#buttonSqrt", hasText("√"));
    }

    @Test
    void should_contain_button_parenthesis() {
        // expect:
        verifyThat("#buttonParenthesisOpen", hasText("(x)"));
    }

    @Test
    void should_contain_button_complexModulus() {
        // expect:
        verifyThat("#buttonComplexModulus", hasText("|x|"));
    }

    @Test
    void should_contain_button_modulo() {
        // expect:
        verifyThat("#buttonModulo", hasText("%"));
    }

    @Test
    void should_contain_button_biggerThan() {
        // expect:
        verifyThat("#buttonBiggerThan", hasText(">"));
    }

    @Test
    void should_contain_button_smallerThan() {
        // expect:
        verifyThat("#buttonLesserThan", hasText("<"));
    }

    @Test
    void should_contain_button_factorial() {
        // expect:
        verifyThat("#buttonFactorial", hasText("!"));
    }

    @Test
    void should_contain_button_toDeg() {
        // expect:
        verifyThat("#buttonToDeg", hasText("DEG"));
    }

    @Test
    void should_contain_button_toRad() {
        // expect:
        verifyThat("#buttonToRad", hasText("RAD"));
    }

    @Test
    void should_contain_button_1() {
        // expect:
        verifyThat("#button1", hasText("1"));
    }

    @Test
    void should_contain_button_2() {
        // expect:
        verifyThat("#button2", hasText("2"));
    }

    @Test
    void should_contain_button_3() {
        // expect:
        verifyThat("#button3", hasText("3"));
    }

    @Test
    void should_contain_button_4() {
        // expect:
        verifyThat("#button4", hasText("4"));
    }

    @Test
    void should_contain_button_5() {
        // expect:
        verifyThat("#button5", hasText("5"));
    }

    @Test
    void should_contain_button_6() {
        // expect:
        verifyThat("#button6", hasText("6"));
    }

    @Test
    void should_contain_button_7() {
        // expect:
        verifyThat("#button7", hasText("7"));
    }

    @Test
    void should_contain_button_8() {
        // expect:
        verifyThat("#button8", hasText("8"));
    }

    @Test
    void should_contain_button_9() {
        // expect:
        verifyThat("#button9", hasText("9"));
    }

    @Test
    void should_contain_button_0() {
        // expect:
        verifyThat("#button0", hasText("0"));
    }

    @Test
    void should_contain_button_comma() {
        // expect:
        verifyThat("#buttonComma", hasText(","));
    }

    @Test
    void should_contain_button_equals() {
        // expect:
        verifyThat("#buttonEquals", hasText("="));
    }

    @Test
    void should_contain_button_erase() {
        // expect:
        verifyThat("#buttonErase", hasText("C"));
    }

    @Test
    void should_contain_button_eraseAll() {
        // expect:
        verifyThat("#buttonEraseAll", hasText("AC"));
    }

    @Test
    void should_contain_button_MoveRight() {
        // expect:
        verifyThat("#buttonMoveRight", hasText("▶"));
    }

    // watch the file calculator.fxml for the id's of the buttons and the file CalculatorController.java for the methods that are called when the buttons are clicked
    @Test
    void should_click_on_button1(FxRobot robot) {
        // when:
        robot.clickOn("#button1");
        // then:
        verifyThat("#equationLabel", hasText("1_"));
    }

    @Test
    void should_click_on_button2(FxRobot robot) {
        // when:
        robot.clickOn("#button2");
        // then:
        verifyThat("#equationLabel", hasText("2_"));
    }

    @Test
    void should_click_on_button3(FxRobot robot) {
        // when:
        robot.clickOn("#button3");
        // then:
        verifyThat("#equationLabel", hasText("3_"));
    }

    @Test
    void should_click_on_button4(FxRobot robot) {
        // when:
        robot.clickOn("#button4");
        // then:
        verifyThat("#equationLabel", hasText("4_"));
    }

    @Test
    void should_click_on_button5(FxRobot robot) {
        // when:
        robot.clickOn("#button5");
        // then:
        verifyThat("#equationLabel", hasText("5_"));
    }

    @Test
    void should_click_on_button6(FxRobot robot) {
        // when:
        robot.clickOn("#button6");
        // then:
        verifyThat("#equationLabel", hasText("6_"));
    }

    @Test
    void should_click_on_button7(FxRobot robot) {
        // when:
        robot.clickOn("#button7");
        // then:
        verifyThat("#equationLabel", hasText("7_"));
    }

    @Test
    void should_click_on_button8(FxRobot robot) {
        // when:
        robot.clickOn("#button8");
        // then:
        verifyThat("#equationLabel", hasText("8_"));
    }

    @Test
    void should_click_on_button9(FxRobot robot) {
        // when:
        robot.clickOn("#button9");
        // then:
        verifyThat("#equationLabel", hasText("9_"));
    }

    @Test
    void should_click_on_button0(FxRobot robot) {
        // when:
        robot.clickOn("#button0");
        // then:
        verifyThat("#equationLabel", hasText("0_"));
    }

    @Test
    void should_click_on_buttonComma(FxRobot robot) {
        // when:
        robot.clickOn("#buttonComma");
        // then:
        verifyThat("#equationLabel", hasText("._"));
    }

    @Test
    void should_click_on_buttonEquals(FxRobot robot) {
        // when:
        robot.clickOn("#buttonEquals");
        // then:
        verifyThat("#equalsLabel", hasText("= 0"));
    }

    @Test
    void should_click_on_buttonErase(FxRobot robot) {
        // when:
        robot.clickOn("#buttonErase");
        // then:
        verifyThat("#equationLabel", hasText(""));
    }

    @Test
    void should_click_on_buttonEraseAll(FxRobot robot) {
        // when:
        robot.clickOn("#buttonEraseAll");
        // then:
        verifyThat("#equationLabel", hasText("_"));
    }

    @Test
    void should_click_on_buttonMoveRight(FxRobot robot) {
        // when:
        robot.clickOn("#buttonMoveRight");
        // then:
        verifyThat("#equationLabel", hasText(""));
    }

    @Test
    void should_click_on_buttonPlus(FxRobot robot) {
        // when:
        robot.clickOn("#buttonPlus");
        // then:
        verifyThat("#equationLabel", hasText("+_"));
    }

    @Test
    void should_click_on_buttonMinus(FxRobot robot) {
        // when:
        robot.clickOn("#buttonMinus");
        // then:
        verifyThat("#equationLabel", hasText("-_"));
    }

    @Test
    void should_click_on_buttonMultiply(FxRobot robot) {
        // when:
        robot.clickOn("#buttonTimes");
        // then:
        verifyThat("#equationLabel", hasText("*_"));
    }

    @Test
    void should_click_on_buttonDivide(FxRobot robot) {
        // when:
        robot.clickOn("#buttonDivide");
        // then:
        verifyThat("#equationLabel", hasText("/_"));
    }

    @Test
    void should_click_on_buttonPower(FxRobot robot) {
        // when:
        robot.clickOn("#buttonExp");
        // then:
        verifyThat("#equationLabel", hasText("e^(_)"));
    }

    @Test
    void should_click_on_buttonRoot(FxRobot robot) {
        // when:
        robot.clickOn("#buttonSqrt");
        // then:
        verifyThat("#equationLabel", hasText("√(_)"));
    }

    @Test
    void should_click_on_buttonFactorial(FxRobot robot) {
        // when:
        robot.clickOn("#buttonFactorial");
        // then:
        verifyThat("#equationLabel", hasText("!_"));
    }

    @Test
    void should_click_on_button1equals(FxRobot robot) {
        // when:
        robot.clickOn("#button1");
        robot.clickOn("#buttonEquals");
        // then:
        verifyThat("#equalsLabel", hasText("= 1"));
    }

    @Test
    void should_click_on_button_cycle(FxRobot robot) {

        verifyThat("#buttonCycleRepresentations", hasText("⟳ | Cart."));
        robot.clickOn("#buttonCycleRepresentations");
        verifyThat("#buttonCycleRepresentations", hasText("⟳ | Exp."));
        robot.clickOn("#buttonCycleRepresentations");
        verifyThat("#buttonCycleRepresentations", hasText("⟳ | Polar"));
        robot.clickOn("#buttonCycleRepresentations");
        verifyThat("#buttonCycleRepresentations", hasText("⟳ | Cart."));

    }

    //make test click on parenthesis buttons, click 1, click go right, click equals and verify that the equals label is 1

    @Test
    void should_click_on_button_moveRightWithValue(FxRobot robot) {
        // when:
        robot.clickOn("#buttonParenthesisOpen");
        robot.clickOn("#button1");
        robot.clickOn("#buttonMoveRight");
        verifyThat("#equationLabel", hasText("(1)_"));
        robot.clickOn("#buttonEquals");
        // then:
        verifyThat("#equalsLabel", hasText("= 1"));
    }

    @Test
    void should_click_on_button_parenthesis_empty(FxRobot robot) {
        // when:
        robot.clickOn("#buttonParenthesisOpen");
        verifyThat("#equationLabel", hasText("(_)"));
    }

    @Test
    void should_click_on_button_up_down_precision (FxRobot robot) {
        // when:
        robot.clickOn("#buttonPrecisionUp");
        verifyThat("#precisionValueLabel", hasText("6"));
        robot.clickOn("#buttonPrecisionDown");
        verifyThat("#precisionValueLabel", hasText("5"));
    }

    @Test
    void shloud_click_erase_with_value(FxRobot robot) {
        // when:
        robot.clickOn("#button1");
        robot.clickOn("#buttonErase");
        verifyThat("#equationLabel", hasText("_"));

        robot.clickOn("#buttonParenthesisOpen");
        robot.clickOn("#buttonErase");
        verifyThat("#equationLabel", hasText("_"));
    }

    /*
    @Test
    void should_click_on_first_button(FxRobot robot) {
        // when:
        robot.clickOn("#first-button");

        // then:
        verifyThat("#first-button", hasText("clicked!"));
    }

    @Test
    void should_click_on_second_button_once(FxRobot robot) {
        // when:
        robot.clickOn("#second-button");

        // then:
        verifyThat("#second-button", hasText("1 clicks"));
        verifyThat("#first-button", hasText("click me to change my name!"));
    }

    @Test
    void should_click_on_second_button_twice(FxRobot robot) {
        // when:
        robot.clickOn("#second-button");
        robot.clickOn("#second-button");

        // then:
        verifyThat("#second-button", hasText("2 clicks"));
        verifyThat("#first-button", hasText("click me to change my name!"));
    }
    */

}