package com.ifmo.lesson14.calc;

import com.ifmo.lesson6.ArrayList;

import java.util.*;

/*
 * Добавьте поддержку переменных.
 * Синтаксис следующий.
 * Объявление переменной через символ '=':
 * x = 8
 *
 * Имя переменной не может быть числом.
 *
 * Использование в выражении вместо одного или двух аргументов:
 * x + 2
 * Результат: 10.
 *
 * Калькулятор должен выбрасывать соответствующие исключения с
 * подробными описаниями ошибок и как их исправить. Например,
 * если имя переменной не найдено или использовался неверный синтаксис.
 */
public class SimpleCalc {
    private static Set<Dim> dim = new HashSet<>();
    public static void main(String[] args) {
        //Set<Dim> dim = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter expression: ");

            String line = scanner.nextLine();

            if ("exit".equals(line))
                break;

            try {
                System.out.println("Answer is: " + calculate(line));
            }
            catch (CalcException e) {
                System.err.println("Error occurred: ");

                e.printStackTrace();
            }
        }
    }

    static int calculate(String line) throws CalcException {


        if (!line.contains("+") && !line.contains("-") && !line.contains("=")) {
            throw new CalcException("Expression must contain '+' or '-': " + line);
        }
        String[] operands = line.split(" ");

        if (operands.length != 3)
            throw new CalcException("Expression must have only 3 operands separated with space (e.g. 2 + 4): " + line);

        if (operands[1].equals("=")) {
            dim.add(new Dim(operands[0], operands[2]));
            System.out.println("Variable have saved");
        } else {

            OPERATOR operator = OPERATOR.parse(operands[1]);

            int op1 = parseOperand(operands[0]);

            int op2 = parseOperand(operands[2]);

            return operator.apply(op1, op2);
        }
        return 0;
    }

    private static int parseOperand(String string) throws CalcException {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            try {
                return dim.stream()
                        .filter(d -> d.getName().equals(string))
                        .findFirst()
                        .get().getValue();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new CalcException("Wrong operand, must be only integer number: " + string, e);
        }
    }

    private static class Dim {
        private String name;
        private int value;
        Dim(String name, String value) throws CalcException {
            this.name=name;
            try {
                this.value = Integer.parseInt(value);
            }catch (NumberFormatException e){
                throw new CalcException("Wrong operand, must be only integer number: " + value, e);
            }
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    private enum OPERATOR {
        PLUS, MINUS;

        int apply(int arg1, int arg2) throws CalcException {
            switch (this) {

                case PLUS:
                    return arg1 + arg2;

                case MINUS:
                    return arg1 - arg2;
            }

            throw new CalcException("Unsupported operator: " + this);
        }

        static OPERATOR parse(String str) throws CalcException {
            switch (str) {
                case "+":
                    return PLUS;

                case "-":
                    return MINUS;
            }

            throw new CalcException("Incorrect operator: " + str);
        }
    }
}
