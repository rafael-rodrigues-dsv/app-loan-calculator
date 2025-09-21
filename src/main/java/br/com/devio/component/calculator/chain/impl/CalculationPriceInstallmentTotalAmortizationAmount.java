package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationPriceInstallmentTotalAmortizationAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue()
                .subtract(currentInstallment.getTotalInterestAmount())
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalAmortizationAmount(totalAmortizationAmount);
        return currentInstallment;
    }
}

