package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.model.InstallmentModel;

public class CalculationPriceInstallmentTotalInstallmentValue extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        currentInstallment.setTotalInstalmentValue(currentInstallment.getTotalPresentValue());
        return currentInstallment;
    }
}