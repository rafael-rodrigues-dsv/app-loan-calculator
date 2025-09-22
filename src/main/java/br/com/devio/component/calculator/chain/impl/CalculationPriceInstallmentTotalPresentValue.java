package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 🧮 Calculadora PMT - valor fixo das parcelas no sistema PRICE
 */
public class CalculationPriceInstallmentTotalPresentValue extends CalculatorEngine<InstalmentModel> {

    private AmountModel totalFinancedAmount;
    private Integer installmentQuantity;

    public CalculationPriceInstallmentTotalPresentValue(AmountModel totalFinancedAmount, Integer installmentQuantity) {
        this.totalFinancedAmount = totalFinancedAmount;
        this.installmentQuantity = installmentQuantity;
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     *           P × r
     * PMT = ─────────────    (ascii)
     *       1 - (1+r)^-n
     * 
     * PMT = (P × r) ÷ (1 - (1+r)^-n)    (algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * P = Principal (valor financiado)
     * r = Taxa mensal (decimal: 2% = 0,02)
     * n = Número de parcelas
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: (100.000 × 0,02) ÷ (1 - (1,02)^-60) = R$ 2.625,22
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstalmentModel calculate(InstalmentModel beforeInstallment, InstalmentModel currentInstallment) {
        if (currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            BigDecimal totalPresentValue = calculatePMT(
                    currentInstallment.getInterestRate(),
                    installmentQuantity,
                    totalFinancedAmount.getAmount()
            );

            currentInstallment.setTotalPresentValue(AmountModel.builder()
                    .amount(totalPresentValue)
                    .currency(CalculationConstant.DEFAULT_CURRENCY)
                    .build());
        } else {
            currentInstallment.setTotalPresentValue(beforeInstallment.getTotalPresentValue());
        }

        return currentInstallment;
    }

    /**
     * 🧮 Calcula o valor da parcela (PMT) usando fórmula financeira
     * 
     * Fórmula matemática:
     *           P × r
     * PMT = ─────────────
     *       1 - (1+r)^-n
     * 
     * Onde:
     * P = Principal (valor financiado)
     * r = Taxa mensal (decimal: 2% = 0,02)
     * n = Número de parcelas
     * 
     * Garante que o empréstimo seja totalmente amortizado
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
