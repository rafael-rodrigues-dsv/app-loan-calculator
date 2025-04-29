package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

import static br.com.example.domain.calculator.constant.CalculationConstant.*;

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
