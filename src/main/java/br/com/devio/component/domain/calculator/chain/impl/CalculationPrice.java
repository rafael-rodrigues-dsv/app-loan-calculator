package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.component.domain.model.FinancialOperationalTaxModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.util.Objects;

public class CalculationPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .addIf(this::hasFinancialOperationalTax,
                        () -> new CalculationTaxInstallment(),
                        () -> new CalculationTotalFinancialOperationalTax(),
                        () -> new CalculationTotalFinancedAmount(),
                        () -> new CalculationPriceInstallment())
                .add(new CalculationTotalLoanAmount())
                .build();

        return chain.calculate(paymentPlanModel);
    }

    private boolean hasFinancialOperationalTax(PaymentPlanModel model) {
        FinancialOperationalTaxModel financialOperationalTax = model.getFinancialOperationalTax();
        return Objects.nonNull(financialOperationalTax)
                && Objects.nonNull(financialOperationalTax.getDailyFinancialOperationalTax())
                && Objects.nonNull(financialOperationalTax.getAdditionalFinancialOperationalTax());
    }
}

