package com.cg;

public class CalculatorService {
    private final ICalculator calculator;

    public CalculatorService(ICalculator calculator) {
        this.calculator = calculator;
    }

    public int addService(int firstNumber, int secondNumber) {
        return calculator.calculate(firstNumber, secondNumber);
    }
}
