package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

import static br.com.example.domain.calculator.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;
import static br.com.example.domain.calculator.constant.CalculationConstant.ROUNDING_MODE;
import static br.com.example.domain.calculator.constant.CalculationConstant.SCALE;

public class CalculationPriceInstallmentTotalBalanceAmount extends CalculatorEngine<InstallmentModel> {

    private static BigDecimal totalFinancedAmount;

    public CalculationPriceInstallmentTotalBalanceAmount(BigDecimal totalFinancedAmount) {
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalBalanceAmount;

        if (currentInstallment.getNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
            totalBalanceAmount = totalFinancedAmount;
        } else {
            totalBalanceAmount = beforeInstallment.getTotalBalanceAmount()
                    .subtract(currentInstallment.getTotalAmortizationAmount())
                    .setScale(SCALE, ROUNDING_MODE);

            if (totalBalanceAmount.compareTo(BigDecimal.ZERO) < 0) {
                totalBalanceAmount = BigDecimal.ZERO;
            }
        }

        currentInstallment.setTotalBalanceAmount(totalBalanceAmount);

        return currentInstallment;
    }
}
