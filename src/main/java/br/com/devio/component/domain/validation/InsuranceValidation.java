package br.com.devio.component.domain.validation;

import br.com.devio.component.domain.model.InsuranceModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class InsuranceValidation extends AbstractValidator<InsuranceModel> {

    @Override
    public void rules() {
        ruleFor(InsuranceModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Payment type must not be null")
                .withFieldName("paymentType");

        ruleFor(InsuranceModel::getTotalValue)
                .must(not(nullValue()))
                .withMessage("Value must not be null")
                .withFieldName("value")
                .must(value -> Objects.nonNull(value) && value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Value must be greater than zero")
                .withFieldName("value");
    }
}
