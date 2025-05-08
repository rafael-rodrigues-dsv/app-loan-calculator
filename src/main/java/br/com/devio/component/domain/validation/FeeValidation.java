package br.com.devio.component.domain.validation;

import br.com.devio.component.domain.model.FeeModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class FeeValidation extends AbstractValidator<FeeModel> {

    @Override
    public void rules() {
        ruleFor(FeeModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Amount cannot be null")
                .withFieldName("paymentType");

        ruleFor(FeeModel::getTotalAmount)
                .must(not(nullValue()))
                .withMessage("Value must not be null")
                .withFieldName("value")
                .must(value -> Objects.nonNull(value) && value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Value must be greater than zero")
                .withFieldName("value");
    }
}
