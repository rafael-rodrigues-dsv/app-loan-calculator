package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.model.InstallmentModel;

import java.math.BigDecimal;

import static br.com.example.domain.calculator.constant.CalculationConstant.ROUNDING_MODE;
import static br.com.example.domain.calculator.constant.CalculationConstant.SCALE;

public class CalculationPriceInstallmentTotalAmortizationAmount extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue()
                .subtract(currentInstallment.getTotalInterestAmount())
                .setScale(SCALE, ROUNDING_MODE);

        currentInstallment.setTotalAmortizationAmount(totalAmortizationAmount);
        return currentInstallment;
    }
}

