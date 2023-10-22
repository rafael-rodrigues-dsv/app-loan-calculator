package br.com.example.domain.calculator.service.calculator.impl;

import br.com.example.domain.calculator.model.PaymentPlanModel;
import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.component.chain.CalculatorEngineBuilder;
import br.com.example.domain.calculator.component.chain.impl.CalculationTotalFinancedAmount;

public class CalculatorPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .build();

        return chain.calculate(paymentPlanModel);
    }
}

