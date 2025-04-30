package br.com.devio.component.domain.calculator.strategy.impl;

import br.com.devio.component.domain.calculator.strategy.CalculationStrategy;
import br.com.devio.component.domain.calculator.template.PaymentPlanGenerator;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.service.impl.CalculatorPriceServiceImpl;

public class PriceCalculationStrategy implements CalculationStrategy {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        PaymentPlanGenerator paymentPlanGenerator = new PaymentPlanGenerator();
        PaymentPlanModel paymentPlanModel = paymentPlanGenerator.generate(loanModel);

        if (paymentPlanModel != null) {
            CalculatorPriceServiceImpl calculatorPriceService = new CalculatorPriceServiceImpl();
            return calculatorPriceService.calculate(paymentPlanModel);
        }

        throw new UnsupportedOperationException("Payment plan model is null");
    }
}
