package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

public class CalculationInstallmentTotalAmortizationAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue()
                .subtract(currentInstallment.getTotalInterestAmount())
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        currentInstallment.setTotalAmortizationAmount(totalAmortizationAmount);
        return currentInstallment;
    }
}

