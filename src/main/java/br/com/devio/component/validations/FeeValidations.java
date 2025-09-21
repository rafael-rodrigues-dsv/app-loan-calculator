package br.com.devio.component.validations;

import br.com.devio.domain.model.FeeModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class FeeValidations extends AbstractValidator<FeeModel> {

    @Override
    public void rules() {
        ruleFor(FeeModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Amount cannot be null")
                .withFieldName("paymentType");

        ruleFor(FeeModel::getTotalAmount)
                .must(not(nullValue()))
                .withMessage("Total amount must not be null")
                .withFieldName("totalAmount")
                .must(amountModel -> Objects.nonNull(amountModel) && Objects.nonNull(amountModel.getAmount()) && amountModel.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Total amount must be greater than zero")
                .withFieldName("totalAmount");
    }
}
