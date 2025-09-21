package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationTaxInstallmentTotalFinancialOperationalTax extends CalculatorEngine<InstallmentModel> {

    @Override
    public InstallmentModel calculate(InstallmentModel currentInstallment) {
        BigDecimal totalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            totalFinancialOperationalTax = Objects.nonNull(currentInstallment.getTotalDailyFinancialOperationalTax())
                    && Objects.nonNull(currentInstallment.getTotalAdditionalFinancialOperationalTax()) ?
                    currentInstallment.getTotalDailyFinancialOperationalTax()
                            .add(currentInstallment.getTotalAdditionalFinancialOperationalTax())
                            .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE)
                    : BigDecimal.ZERO;
        }

        currentInstallment.setTotalFinancialOperationalTax(totalFinancialOperationalTax);

        return currentInstallment;
    }
}