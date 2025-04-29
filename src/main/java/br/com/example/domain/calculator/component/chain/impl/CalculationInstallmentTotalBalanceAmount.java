package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationInstallmentTotalBalanceAmount extends CalculatorEngine<InstallmentModel> {

    private static BigDecimal totalFinancedAmount;

    public CalculationInstallmentTotalBalanceAmount(BigDecimal totalFinancedAmount) {
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalBalanceAmount;

        if (currentInstallment.getNumber().equals(0)) {
            totalBalanceAmount = totalFinancedAmount;
        } else {
            totalBalanceAmount = beforeInstallment.getTotalBalanceAmount()
                    .subtract(currentInstallment.getTotalAmortizationAmount())
                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);

            if (totalBalanceAmount.compareTo(BigDecimal.ZERO) < 0) {
                totalBalanceAmount = BigDecimal.ZERO;
            }
        }

        currentInstallment.setTotalBalanceAmount(totalBalanceAmount);

        return currentInstallment;
    }
}
