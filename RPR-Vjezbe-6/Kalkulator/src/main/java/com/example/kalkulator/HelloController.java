package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class HelloController {

    public TextField Text;

    @FXML
    private void Click_Button_0(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("0");
    }

    public void Click_Button_tacka(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {
            Text.appendText("0");
            Text.appendText(".");
        }
        else if (string.contains("."))
        {;}
        else {
            Text.appendText(".");
        }
    }

    public void Click_Button_1(ActionEvent actionEvent) {
        Text.appendText("1");
    }

    public void Click_Button_Modulo(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("%");
    }

    public void Click_Button_Podijeljeno(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("/");
    }

    public void Click_Button_Puta(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("*");
    }

    public void Click_Button_Minus(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("-");
    }

    public void Click_Button_7(ActionEvent actionEvent) {
        Text.appendText("7");
    }

    public void Click_Button_8(ActionEvent actionEvent) {
        Text.appendText("8");
    }

    public void Click_Button_9(ActionEvent actionEvent) {
        Text.appendText("9");
    }

    public void Click_Button_Plus(ActionEvent actionEvent) {
        String string=Text.getText();
        if(string.isEmpty())
        {;}
        else
        Text.appendText("+");
    }

    @FXML
    private void Click_Button_Jednako(ActionEvent actionEvent) {
        String string = Text.getText();

        if (!JelPrazan(string)) {
            try {
                double result = Uradi(string);
                Text.setText(Double.toString(result));
            } catch (NumberFormatException | ArithmeticException e) {
                Text.setText("Error");
            }
        }
    }

    private double Uradi(String string) {
        List<String> tokens = tokenizeExpression(string);

        // Liste koje cuvaju operatore i operande
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();

        // Popuni liste
        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                operands.add(Double.parseDouble(token));
            } else if (!token.isEmpty()) {
                operators.add(token);
            }
        }

        // Izracunaj
        double result = operands.get(0);
        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            double operand = operands.get(i + 1);

            // Switch statement to handle different operators
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
                case "%":
                    result %= operand;
                    break;
                default:
                    throw new ArithmeticException("Invalid operator: " + operator);
            }
        }

        return result;
    }

    private List<String> tokenizeExpression(String string) {
        List<String> tokens = new ArrayList<>();
        StringBuilder trenutniToken = new StringBuilder();

        for (char c : string.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                trenutniToken.append(c);
            } else {
                if (trenutniToken.length() > 0) {
                    tokens.add(trenutniToken.toString());
                    trenutniToken = new StringBuilder();
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (trenutniToken.length() > 0) {
            tokens.add(trenutniToken.toString());
        }

        return tokens;
    }
    public boolean JelPrazan(String string)
    {
        if(string.isEmpty())
            return true;
        return false;
    }

    public void Click_Button_6(ActionEvent actionEvent) {
        Text.appendText("6");
    }

    public void Click_Button_3(ActionEvent actionEvent) {
        Text.appendText("3");
    }

    public void Click_Button_5(ActionEvent actionEvent) {
        Text.appendText("5");
    }

    public void Click_Button_4(ActionEvent actionEvent) {
        Text.appendText("4");
    }

    public void Click_Button_2(ActionEvent actionEvent) {
        Text.appendText("2");
    }

    public void Click_Button_Clear(ActionEvent actionEvent) {
        Text.setText("");
    }
}