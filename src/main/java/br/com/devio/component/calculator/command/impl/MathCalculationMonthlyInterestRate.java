package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.component.calculator.command.input.MathCalculationPowerInput;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_MONTH;
import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_YEAR;
import static br.com.devio.domain.constant.CalculationConstant.ROUNDING_MODE_HALF_UP;
import static br.com.devio.domain.constant.CalculationConstant.SCALE_10;

public class MathCalculationMonthlyInterestRate implements MathCalculationCommand<BigDecimal, BigDecimal> {
    private static final MathCalculationPow POWER_CALCULATOR = new MathCalculationPow();

    @Override
    public BigDecimal execute(BigDecimal input) {
        BigDecimal annualInterestRate = Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("Annual interest rate cannot be null"));

        BigDecimal daysInMonth = BigDecimal.valueOf(DAYS_IN_MONTH);
        BigDecimal daysInYear = BigDecimal.valueOf(DAYS_IN_YEAR);
        BigDecimal exponent = daysInMonth
                .divide(daysInYear,
                        SCALE_10,
                        ROUNDING_MODE_HALF_UP);
        BigDecimal base = BigDecimal.ONE
                .add(annualInterestRate);

        BigDecimal result =
                calculatePower(base, exponent)
                        .subtract(BigDecimal.ONE)
                        .multiply(BigDecimal.valueOf(100));

        return result.setScale(SCALE_10, ROUNDING_MODE_HALF_UP);
    }

    private BigDecimal calculatePower(BigDecimal base, BigDecimal exponent) {
        return POWER_CALCULATOR.execute(MathCalculationPowerInput.builder()
                .base(base)
                .exponent(exponent)
                .build());
    }
}
