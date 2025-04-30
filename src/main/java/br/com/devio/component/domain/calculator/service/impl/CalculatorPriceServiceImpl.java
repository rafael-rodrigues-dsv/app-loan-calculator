package br.com.devio.component.domain.calculator.service.impl;

import br.com.devio.component.domain.calculator.component.chain.CalculatorEngine;
import br.com.devio.component.domain.calculator.component.chain.impl.CalculationPriceInstallment;
import br.com.devio.component.domain.calculator.component.chain.impl.CalculationTotalFinancedAmount;
import br.com.devio.component.domain.calculator.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.component.chain.CalculatorEngineBuilder;

public class CalculatorPriceServiceImpl {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .build();

        return chain.calculate(paymentPlanModel);
    }
}

