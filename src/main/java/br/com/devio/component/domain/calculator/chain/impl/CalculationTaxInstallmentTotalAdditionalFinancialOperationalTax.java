package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.model.InstallmentModel;
import br.com.devio.component.domain.model.TaxModel;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax extends CalculatorEngine<InstallmentModel> {
    private TaxModel taxModel;
    private BigDecimal totalFinancedAmount;

    public CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax(TaxModel taxModel, BigDecimal totalFinancedAmount) {
        this.taxModel = taxModel;
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalAdditionalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)
                && (Objects.nonNull(taxModel)
                && taxModel.getTaxType().equals(TaxTypeEnum.ADDITIONAL_IOF))) {
            totalAdditionalFinancialOperationalTax = totalFinancedAmount
                    .multiply(taxModel.getValue())
                    .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalAdditionalFinancialOperationalTax(totalAdditionalFinancialOperationalTax);

        return currentInstallment;
    }
}