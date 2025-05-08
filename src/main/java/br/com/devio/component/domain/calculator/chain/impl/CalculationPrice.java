package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.component.domain.model.InstallmentModel;
import br.com.devio.component.domain.model.TaxModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.util.List;
import java.util.Objects;

public class CalculationPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .addIf(this::hasFinancialOperationalTax,
                        () -> new CalculationTaxInstallment(),
                        () -> new CalculationTotalTaxAmount(),
                        () -> new CalculationTotalFinancedAmount(),
                        () -> new CalculationPriceInstallment())
                .add(new CalculationTotalGrantedAmount())
                .add(new CalculationTotalLoanAmount())
                .build();

        List<InstallmentModel> installments = paymentPlanModel.getInstallments();

        if(Objects.nonNull(installments) && !installments.isEmpty()) {
            paymentPlanModel.setInstallments(installments
                    .stream()
                    .filter(f -> f.getNumber() != 0L)
                    .toList());
        }

        return chain.calculate(paymentPlanModel);
    }

    private boolean hasFinancialOperationalTax(PaymentPlanModel model) {
        TaxModel financialOperationalTax = model.getTax();
        return Objects.nonNull(financialOperationalTax)
                && Objects.nonNull(financialOperationalTax.getDailyFinancialOperationalTax())
                && Objects.nonNull(financialOperationalTax.getAdditionalFinancialOperationalTax());
    }
}

