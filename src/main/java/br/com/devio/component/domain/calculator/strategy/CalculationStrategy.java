package br.com.devio.component.domain.calculator.strategy;

import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

public interface CalculationStrategy {
    PaymentPlanModel calculate(LoanModel loanModel);
}
