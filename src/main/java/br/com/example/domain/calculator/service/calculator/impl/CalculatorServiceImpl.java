package br.com.example.domain.calculator.service.calculator.impl;

import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;
import br.com.example.domain.calculator.component.factory.CalculatorFactory;
import br.com.example.domain.calculator.service.calculator.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        return CalculatorFactory.calculate(loanModel);
    }
}
