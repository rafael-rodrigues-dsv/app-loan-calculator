package br.com.devio.component.validations;

import br.com.devio.domain.model.InsuranceModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class InsuranceValidations extends AbstractValidator<InsuranceModel> {

    @Override
    public void rules() {
        ruleFor(InsuranceModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Payment type must not be null")
                .withFieldName("paymentType");

        ruleFor(InsuranceModel::getTotalAmount)
                .must(not(nullValue()))
                .withMessage("Total amount must not be null")
                .withFieldName("totalAmount")
                .must(amountModel -> Objects.nonNull(amountModel) && Objects.nonNull(amountModel.getAmount()) && amountModel.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Total amount must be greater than zero")
                .withFieldName("totalAmount");
    }
}
