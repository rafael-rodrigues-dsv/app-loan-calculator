package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

import static br.com.example.domain.calculator.constant.CalculationConstant.PERCENTAGE_DIVISOR_100;
import static br.com.example.domain.calculator.constant.CalculationConstant.ROUNDING_MODE;
import static br.com.example.domain.calculator.constant.CalculationConstant.SCALE;

public class CalculationPriceInstallmentTotalInterestAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal interestRate = currentInstallment.getInterestRate();

        BigDecimal totalInterestAmount = interestRate.multiply(beforeInstallment.getTotalBalanceAmount())
                .divide(PERCENTAGE_DIVISOR_100)
                .setScale(SCALE, ROUNDING_MODE);

        currentInstallment.setTotalInterestAmount(totalInterestAmount);

        return currentInstallment;
    }
}
