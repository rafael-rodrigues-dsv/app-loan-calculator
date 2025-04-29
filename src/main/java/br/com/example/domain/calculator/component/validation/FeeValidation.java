package br.com.example.domain.calculator.component.validation;

import br.com.example.domain.calculator.model.FeeModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;

public class FeeValidation extends AbstractValidator<FeeModel> {

    @Override
    public void rules() {
        ruleFor(FeeModel::getPaymentType)
                .must(paymentType -> paymentType != null)
                .withMessage("Payment type must not be null");

        ruleFor(FeeModel::getValue)
                .must(value -> value != null)
                .withMessage("Value must not be null")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .when(value -> value != null)
                .withMessage("Value must be greater than zero");
    }
}
