package br.com.devio.domain.usecase.impl;

import br.com.devio.component.calculator.factory.CalculatorFactory;
import br.com.devio.component.validations.CalculatorValidations;
import br.com.devio.infraestructure.exception.BadRequestException;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;
import br.com.devio.domain.usecase.SimulationUseCase;
import br.com.fluentvalidator.context.ValidationResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulationUseCaseImpl implements SimulationUseCase {

    @Override
    public PaymentPlanModel executeSimulation(LoanModel loanModel) {
        ValidationResult validation = new CalculatorValidations().validate(loanModel);

        if (!validation.isValid()) {
            validation.isInvalidThrow(BadRequestException.class);
        }

        return CalculatorFactory.calculate(loanModel);
    }
}
