package br.com.devio.domain.service.impl;

import br.com.devio.component.calculator.factory.CalculatorFactory;
import br.com.devio.component.validations.CalculatorValidations;
import br.com.devio.domain.exception.CalculatorValidationException;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;
import br.com.devio.domain.service.CalculatorService;
import br.com.fluentvalidator.context.ValidationResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public PaymentPlanModel calculate(LoanModel loanModel) {
        ValidationResult validation = new CalculatorValidations().validate(loanModel);

        if (!validation.isValid()) {
            validation.isInvalidThrow(CalculatorValidationException.class);
        }

        return CalculatorFactory.calculate(loanModel);
    }
}
