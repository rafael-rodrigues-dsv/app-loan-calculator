package br.com.example.domain.calculator.service.impl;

import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;
import br.com.example.domain.calculator.component.factory.CalculatorFactory;
import br.com.example.domain.calculator.service.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        return CalculatorFactory.calculate(loanModel);
    }
}
