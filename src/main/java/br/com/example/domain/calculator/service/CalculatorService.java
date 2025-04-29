package br.com.example.domain.calculator.service;

import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;

public interface CalculatorService {
    PaymentPlanModel calculate(LoanModel loanModel);
}
