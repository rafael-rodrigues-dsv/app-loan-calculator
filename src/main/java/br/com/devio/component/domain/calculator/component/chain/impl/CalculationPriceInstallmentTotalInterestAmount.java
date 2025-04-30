package br.com.devio.component.domain.calculator.component.chain.impl;

import br.com.devio.component.domain.calculator.constant.CalculationConstant;
import br.com.devio.component.domain.calculator.model.InstallmentModel;
import br.com.devio.component.domain.calculator.component.chain.CalculatorEngine;

import java.math.BigDecimal;

public class CalculationPriceInstallmentTotalInterestAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal interestRate = currentInstallment.getInterestRate();

        BigDecimal totalInterestAmount = interestRate.multiply(beforeInstallment.getTotalBalanceAmount())
                .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                .setScale(CalculationConstant.SCALE, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalInterestAmount(totalInterestAmount);

        return currentInstallment;
    }
}
