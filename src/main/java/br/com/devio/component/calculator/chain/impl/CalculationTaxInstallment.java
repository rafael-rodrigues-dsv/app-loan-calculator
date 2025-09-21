package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.TaxModel;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

public class CalculationTaxInstallment extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        List<InstallmentModel> installments = new ArrayList<>();
        final BigDecimal totalFinancedAmount = paymentPlanModel.getTotalFinancedAmount();
        final TaxModel financialOperationalTax =  paymentPlanModel.getTax();
        final BigDecimal dailyFinancialOperationalTax = Objects.nonNull(financialOperationalTax)
                ? financialOperationalTax.getDailyFinancialOperationalTax()
                : BigDecimal.ZERO;
        final BigDecimal additionalFinancialOperationalTax = Objects.nonNull(financialOperationalTax)
                ? financialOperationalTax.getAdditionalFinancialOperationalTax()
                : BigDecimal.ZERO;

        paymentPlanModel.getInstallments().forEach(currentInstallment -> {
            if (!currentInstallment.getInstallmentNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationTaxInstallmentTotalDailyFinancialOperationalTax(dailyFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax(additionalFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalFinancialOperationalTax())
                        .build();

                currentInstallment = chain.calculate(currentInstallment);
            }

            installments.add(currentInstallment);
        });

        paymentPlanModel.setInstallments(installments);

        return paymentPlanModel;
    }
}