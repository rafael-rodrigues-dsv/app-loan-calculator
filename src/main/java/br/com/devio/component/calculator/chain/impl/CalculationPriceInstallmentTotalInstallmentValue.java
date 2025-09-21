package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.model.InstallmentModel;

public class CalculationPriceInstallmentTotalInstallmentValue extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        currentInstallment.setTotalInstalmentValue(currentInstallment.getTotalPresentValue());
        return currentInstallment;
    }
}