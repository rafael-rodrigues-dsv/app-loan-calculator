package br.com.example.domain.service.calculator.impl;

import br.com.example.domain.model.LoanModel;
import br.com.example.domain.model.PaymentPlanModel;
import br.com.example.domain.service.calculator.CalculatorFactory;
import br.com.example.domain.service.calculator.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        return CalculatorFactory.calculate(loanModel);
    }
}
