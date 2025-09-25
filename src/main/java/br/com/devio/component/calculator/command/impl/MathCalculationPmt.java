package br.com.devio.component.calculator.command.impl;

import br.com.devio.domain.constant.CalculationConstant;

import java.math.BigDecimal;
import java.math.MathContext;

public class MathCalculationPmt {

    private BigDecimal calculatePMT(BigDecimal interestRate, Integer installmentQuantity, BigDecimal totalFinancedAmount) {
        if (interestRate == null || installmentQuantity == null || totalFinancedAmount == null) {
            throw new IllegalArgumentException("Interest rate, total periods, and total financed amount must not be null.");
        }

        if (interestRate.compareTo(BigDecimal.ZERO) <= 0 || installmentQuantity <= 0) {
            throw new IllegalArgumentException("Interest rate must be greater than zero and total periods must be positive.");
        }

        BigDecimal monthlyRate = interestRate.divide(CalculationConstant.PERCENTAGE_DIVISOR_100, MathContext.DECIMAL128);
        BigDecimal numerator = monthlyRate.multiply(totalFinancedAmount);
        BigDecimal denominator = BigDecimal.ONE.subtract(
                BigDecimal.ONE.add(monthlyRate).pow(-installmentQuantity, MathContext.DECIMAL128)
        );

        return numerator.divide(denominator, CalculationConstant.SCALE_2, BigDecimal.ROUND_HALF_EVEN);
    }
}
