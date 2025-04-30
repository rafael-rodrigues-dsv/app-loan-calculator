package br.com.devio.component.domain.calculator.component.chain.impl;

import br.com.devio.component.domain.calculator.constant.CalculationConstant;
import br.com.devio.component.domain.calculator.model.InstallmentModel;
import br.com.devio.component.domain.calculator.component.chain.CalculatorEngine;

import java.math.BigDecimal;

public class CalculationPriceInstallmentTotalAmortizationAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue()
                .subtract(currentInstallment.getTotalInterestAmount())
                .setScale(CalculationConstant.SCALE, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalAmortizationAmount(totalAmortizationAmount);
        return currentInstallment;
    }
}

