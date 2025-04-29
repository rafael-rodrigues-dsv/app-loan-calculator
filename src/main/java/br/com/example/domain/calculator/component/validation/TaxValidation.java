package br.com.example.domain.calculator.component.validation;

import br.com.example.domain.calculator.model.TaxModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class TaxValidation extends AbstractValidator<TaxModel> {

    @Override
    public void rules() {
        ruleFor(TaxModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Payment type cannot be null")
                .withFieldName("modalityType");

        ruleFor(TaxModel::getValue)
                .must(value -> value != null)
                .withMessage("Value must not be null")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .when(value -> value != null)
                .withMessage("Value must be greater than zero");

        ruleFor(TaxModel::getTaxType)
                .must(not(nullValue()))
                .withMessage("Tax type cannot be null")
                .withFieldName("taxType");
    }
}

