package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculationPriceInstallmentTotalPresentValue extends CalculatorEngine<InstallmentModel> {

    private static BigDecimal totalFinancedAmount;
    private static Integer installmentQuantity;

    public CalculationPriceInstallmentTotalPresentValue(BigDecimal totalFinancedAmount, Integer installmentQuantity) {
        this.totalFinancedAmount = totalFinancedAmount;
        this.installmentQuantity = installmentQuantity;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        if (currentInstallment.getNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            BigDecimal totalPresentValue = calculatePMT(
                    currentInstallment.getInterestRate(),
                    installmentQuantity,
                    totalFinancedAmount
            );

            currentInstallment.setTotalPresentValue(totalPresentValue);
        } else {
            currentInstallment.setTotalPresentValue(beforeInstallment.getTotalPresentValue());
        }

        return currentInstallment;
    }

    /**
     * This method calculates the total installment value (PMT) for a loan installment.
     * It uses the PMT formula to determine the fixed payment amount based on
     * the principal amount, interest rate, and total number of periods.
     * <p>
     * The formula used is:
     * PMT = (P * r) / (1 - (1 + r)^-n)
     * Where:
     * - P is the principal amount (loan amount)
     * - r is the monthly interest rate (as a decimal, divided by 100).
     * - n is the total number of periods (installments).
     * <p>
     * Parameters:
     *
     * @param interestRate        The annual interest rate (as a percentage).
     * @param installmentQuantity The total number of installments for the loan.
     * @param totalFinancedAmount The total amount financed (principal).
     *                            <p>
     *                            This ensures that the loan is fully amortized over the specified number of installments.
     */
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
