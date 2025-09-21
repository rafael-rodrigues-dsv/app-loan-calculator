package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationPriceInstallmentTotalBalanceAmount extends CalculatorEngine<InstallmentModel> {

    private static BigDecimal totalFinancedAmount;

    public CalculationPriceInstallmentTotalBalanceAmount(BigDecimal totalFinancedAmount) {
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalBalanceAmount;

        if (currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            totalBalanceAmount = totalFinancedAmount;
        } else {
            totalBalanceAmount = beforeInstallment.getTotalBalanceAmount()
                    .subtract(currentInstallment.getTotalAmortizationAmount())
                    .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

            if (totalBalanceAmount.compareTo(BigDecimal.ZERO) < 0) {
                totalBalanceAmount = BigDecimal.ZERO;
            }
        }

        currentInstallment.setTotalBalanceAmount(totalBalanceAmount);

        return currentInstallment;
    }
}
