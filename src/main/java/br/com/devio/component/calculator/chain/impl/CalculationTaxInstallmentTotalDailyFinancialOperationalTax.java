package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationTaxInstallmentTotalDailyFinancialOperationalTax extends CalculatorEngine<InstallmentModel> {
    private BigDecimal dailyFinancialOperationalTax;
    private BigDecimal totalFinancedAmount;

    public CalculationTaxInstallmentTotalDailyFinancialOperationalTax(BigDecimal dailyFinancialOperationalTax, BigDecimal totalFinancedAmount) {
        this.dailyFinancialOperationalTax = dailyFinancialOperationalTax;
        this.totalFinancedAmount = totalFinancedAmount;
    }

    @Override
    public InstallmentModel calculate(InstallmentModel currentInstallment) {
        BigDecimal totalDailyFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)
                && Objects.nonNull(dailyFinancialOperationalTax)) {
            totalDailyFinancialOperationalTax = totalFinancedAmount
                    .multiply(dailyFinancialOperationalTax)
                    .multiply(BigDecimal.valueOf(currentInstallment.getContractDays()))
                    .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalDailyFinancialOperationalTax(totalDailyFinancialOperationalTax);

        return currentInstallment;
    }
}