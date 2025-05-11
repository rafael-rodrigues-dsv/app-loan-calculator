package br.com.devio.component.domain.calculator.command.impl;

import br.com.devio.component.domain.calculator.command.MathCalculationCommand;

import static br.com.devio.component.domain.constant.CalculationConstant.DAYS_IN_MONTH;
import static br.com.devio.component.domain.constant.CalculationConstant.DAYS_IN_YEAR;

public class MathCalculationMonthlyInterestRate implements MathCalculationCommand<Double, Double> {

    @Override
    public Double execute(Double annualInterestRate) {
        return (Math.pow(1 + annualInterestRate, DAYS_IN_MONTH / DAYS_IN_YEAR) - 1) * 100;
    }
}
