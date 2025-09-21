package br.com.devio.component.validations;

import br.com.devio.domain.model.TaxModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class TaxValidations extends AbstractValidator<TaxModel> {

    @Override
    public void rules() {
        ruleFor(TaxModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Payment type cannot be null")
                .withFieldName("paymentType");

        ruleFor(TaxModel::getDailyFinancialOperationalTax)
                .must(not(nullValue()))
                .withMessage("Daily financial operational tax must not be null")
                .withFieldName("dailyFinancialOperationalTax")
                .must(amountModel -> Objects.nonNull(amountModel) && Objects.nonNull(amountModel.getAmount()) && amountModel.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Daily financial operational tax must be greater than zero")
                .withFieldName("dailyFinancialOperationalTax");

        ruleFor(TaxModel::getAdditionalFinancialOperationalTax)
                .must(not(nullValue()))
                .withMessage("Additional financial operational tax must not be null")
                .withFieldName("additionalFinancialOperationalTax")
                .must(amountModel -> Objects.nonNull(amountModel) && Objects.nonNull(amountModel.getAmount()) && amountModel.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Additional financial operational tax must be greater than zero")
                .withFieldName("additionalFinancialOperationalTax");
    }
}

