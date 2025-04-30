package br.com.devio.component.domain.calculator.component.validation;

import br.com.devio.component.domain.calculator.model.InsuranceModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;

public class InsuranceValidation extends AbstractValidator<InsuranceModel> {

    @Override
    public void rules() {
        ruleFor(InsuranceModel::getPaymentType)
                .must(paymentType -> paymentType != null)
                .withMessage("Payment type must not be null");

        ruleFor(InsuranceModel::getValue)
                .must(value -> value != null)
                .withMessage("Value must not be null")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .when(value -> value != null)
                .withMessage("Value must be greater than zero");
    }
}
