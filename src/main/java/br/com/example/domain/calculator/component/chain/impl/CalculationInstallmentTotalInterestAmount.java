package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationInstallmentTotalInterestAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal interestRate = currentInstallment.getInterestRate();

        BigDecimal totalInterestAmount = interestRate.multiply(beforeInstallment.getTotalBalanceAmount())
                .divide(BigDecimal.valueOf(100))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        currentInstallment.setTotalInterestAmount(totalInterestAmount);

        return currentInstallment;
    }
}
