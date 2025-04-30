package br.com.devio.component.domain.calculator.service;

import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

public interface CalculatorService {
    PaymentPlanModel calculate(LoanModel loanModel);
}
