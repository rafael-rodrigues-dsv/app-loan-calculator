package br.com.example.domain.service.calculator.impl;

import br.com.example.domain.model.PaymentPlanModel;
import br.com.example.domain.service.engine.CalculatorEngine;
import br.com.example.domain.service.engine.CalculatorEngineBuilder;
import br.com.example.domain.service.engine.impl.CalculationTotalFinancedAmount;

public class CalculatorPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .build();

        return chain.calculate(paymentPlanModel);
    }
}

