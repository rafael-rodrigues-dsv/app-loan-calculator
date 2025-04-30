package br.com.devio.component.domain.calculator.service.impl;

import br.com.devio.component.domain.calculator.factory.CalculatorFactory;
import br.com.devio.component.domain.validation.CalculatorValidation;
import br.com.devio.component.domain.exception.CalculatorValidationException;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.service.CalculatorService;
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
