package br.com.example.domain.service.calculator;

import br.com.example.domain.model.LoanModel;
import br.com.example.domain.model.PaymentPlanModel;

public interface CalculatorService {
    PaymentPlanModel calculate(LoanModel loanModel);
}
