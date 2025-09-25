package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.component.calculator.command.input.MathCalculationNetPresentValueInput;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.ROUNDING_MODE_HALF_UP;
import static br.com.devio.domain.constant.CalculationConstant.SCALE_10;

public class MathCalculationXirr implements MathCalculationCommand<List<InstalmentModel>, BigDecimal> {
    private static final BigDecimal LOWER_BOUND = BigDecimal.valueOf(-1.0);
    private static final BigDecimal UPPER_BOUND = BigDecimal.valueOf(1.0);
    private static final BigDecimal TOLERANCE = BigDecimal.valueOf(0.000001);
    private static final Integer MAX_ITERATIONS = 1000;
    private static final MathCalculationNetPresetValue MATH_CALCULATION_NET_PRESENT_VALUE = new MathCalculationNetPresetValue();

    @Override
    public BigDecimal execute(List<InstalmentModel> input) {
        List<InstalmentModel> installments = Optional.ofNullable(input)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Installments list cannot be null or empty"));

        BigDecimal lowerBound = LOWER_BOUND;
        BigDecimal upperBound = UPPER_BOUND;
        int count = 0;

        while (count <= MAX_ITERATIONS) {
            count++;
            BigDecimal guess = lowerBound.add(upperBound)
                    .divide(BigDecimal.valueOf(2),
                            SCALE_10,
                            ROUNDING_MODE_HALF_UP);

            BigDecimal totalNetPresentValue = calculateNetPresentValue(guess, installments);

            if (totalNetPresentValue.abs().compareTo(TOLERANCE) < 0) {
                return guess;
            } else if (totalNetPresentValue.compareTo(BigDecimal.ZERO) > 0) {
                lowerBound = guess;
            } else {
                upperBound = guess;
            }
        }

        throw new RuntimeException("XIRR calculation did not converge");
    }

    private BigDecimal calculateNetPresentValue(BigDecimal guess, List<InstalmentModel> installments) {
        return MATH_CALCULATION_NET_PRESENT_VALUE.execute(MathCalculationNetPresentValueInput.builder()
                .installments(installments)
                .interestRate(guess)
                .build());
    }
}