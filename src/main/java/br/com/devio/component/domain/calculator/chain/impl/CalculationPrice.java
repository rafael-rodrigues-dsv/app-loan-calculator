package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.component.domain.model.PaymentPlanModel;

public class CalculationPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .add(new CalculationTaxInstallment())
                .add(new CalculationTotalLoanAmount())
                .build();

        return chain.calculate(paymentPlanModel);
    }
}

