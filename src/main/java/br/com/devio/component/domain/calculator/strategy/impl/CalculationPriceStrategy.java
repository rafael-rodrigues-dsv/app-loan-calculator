package br.com.devio.component.domain.calculator.strategy.impl;

import br.com.devio.component.domain.calculator.strategy.CalculationStrategy;
import br.com.devio.component.domain.calculator.template.PaymentPlanGenerator;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.chain.impl.CalculationPrice;

public class CalculationPriceStrategy implements CalculationStrategy {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        PaymentPlanGenerator paymentPlanGenerator = new PaymentPlanGenerator();
        PaymentPlanModel paymentPlanModel = paymentPlanGenerator.generate(loanModel);

        if (paymentPlanModel != null) {
            CalculationPrice calculationPrice = new CalculationPrice();
            return calculationPrice.calculate(paymentPlanModel);
        }

        throw new UnsupportedOperationException("Payment plan model is null");
    }
}
