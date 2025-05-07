package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.model.InstallmentModel;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax extends CalculatorEngine<InstallmentModel> {
    private BigDecimal additionalFinancialOperationalTax;
    private BigDecimal totalFinancedAmount;

    public CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax(BigDecimal additionalFinancialOperationalTax, BigDecimal totalFinancedAmount) {
        this.additionalFinancialOperationalTax = additionalFinancialOperationalTax;
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel currentInstallment) {
        BigDecimal totalAdditionalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)
                && Objects.nonNull(additionalFinancialOperationalTax)) {
            totalAdditionalFinancialOperationalTax = totalFinancedAmount
                    .multiply(additionalFinancialOperationalTax)
                    .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalAdditionalFinancialOperationalTax(totalAdditionalFinancialOperationalTax);

        return currentInstallment;
    }
}