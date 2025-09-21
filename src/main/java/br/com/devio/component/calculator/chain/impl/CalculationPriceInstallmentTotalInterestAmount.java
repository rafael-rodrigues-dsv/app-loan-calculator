package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationPriceInstallmentTotalInterestAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal interestRate = currentInstallment.getInterestRate();

        BigDecimal totalInterestAmount = interestRate.multiply(beforeInstallment.getTotalBalanceAmount())
                .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalInterestAmount(totalInterestAmount);

        return currentInstallment;
    }
}
