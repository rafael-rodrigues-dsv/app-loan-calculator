package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.model.InstallmentModel;

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
