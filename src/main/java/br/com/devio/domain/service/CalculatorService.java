package br.com.devio.domain.service;

import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;

public interface CalculatorService {
    PaymentPlanModel calculate(LoanModel loanModel);
}
