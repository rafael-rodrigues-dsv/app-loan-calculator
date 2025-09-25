package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.component.calculator.command.input.MathCalculationPowerInput;

import java.math.BigDecimal;

public class MathCalculationPow implements MathCalculationCommand<MathCalculationPowerInput, BigDecimal> {

    @Override
    public BigDecimal execute(MathCalculationPowerInput input) {
        if (input == null || input.getBase() == null || input.getExponent() == null) {
            throw new IllegalArgumentException("Base and exponent must not be null");
        }

        double baseDouble = input.getBase().doubleValue();
        double exponentDouble = input.getExponent().doubleValue();
        double result = Math.pow(baseDouble, exponentDouble);

        return new BigDecimal(String.valueOf(result));
    }
}