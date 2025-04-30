package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.model.InstallmentModel;

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

