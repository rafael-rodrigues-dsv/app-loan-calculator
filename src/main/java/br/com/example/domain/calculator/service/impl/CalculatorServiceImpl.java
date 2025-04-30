package br.com.example.domain.calculator.service.impl;

import br.com.example.domain.calculator.component.factory.CalculatorFactory;
import br.com.example.domain.calculator.component.validation.CalculatorValidation;
import br.com.example.domain.calculator.exception.CalculatorValidationException;
import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;
import br.com.example.domain.calculator.service.CalculatorService;
import br.com.fluentvalidator.context.ValidationResult;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        ValidationResult validation = new CalculatorValidation().validate(loanModel);

        if (!validation.isValid()) {
            validation.isInvalidThrow(CalculatorValidationException.class);
        }

        return CalculatorFactory.calculate(loanModel);
    }
}
