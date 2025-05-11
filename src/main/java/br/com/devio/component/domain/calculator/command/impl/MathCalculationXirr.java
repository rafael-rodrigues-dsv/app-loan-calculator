package br.com.devio.component.domain.calculator.command.impl;

import br.com.devio.component.domain.calculator.command.MathCalculationCommand;
import br.com.devio.component.domain.model.InstallmentModel;

import java.util.List;

public class MathCalculationXirr implements MathCalculationCommand<List<InstallmentModel>, Double> {

    private static final Double LOWER_BOUND = -1.0;
    private static final Double UPPER_BOUND = 1.0;
    private static final Double TOLERANCE = 0.000001;
    private static final int MAX_ITERATIONS = 1000;

    @Override
    public Double execute(List<InstallmentModel> installments) {
        Double lowerBound = LOWER_BOUND;
        Double upperBound = UPPER_BOUND;
        int count = 0;

        while (count <= MAX_ITERATIONS) {
            count++;
            Double guess = (lowerBound + upperBound) / 2.0;
            Double totalNetPresentValue = new MathCalculationNetPresentValue(guess).execute(installments);
            if (Math.abs(totalNetPresentValue) < TOLERANCE) {
                return guess;
            } else if (totalNetPresentValue > 0) {
                lowerBound = guess;
            } else {
                upperBound = guess;
            }
        }

        throw new RuntimeException("XIRR calculation did not converge");
    }
}
