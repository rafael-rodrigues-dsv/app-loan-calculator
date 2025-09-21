package br.com.devio.component.validations;

import br.com.devio.domain.model.TaxModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;

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
                .must(value -> value != null)
                .withMessage("Daily financial operational tax must not be null")
                .withFieldName("dailyFinancialOperationalTax")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Daily financial operational tax Value must be greater than zero")
                .withFieldName("dailyFinancialOperationalTax");

        ruleFor(TaxModel::getAdditionalFinancialOperationalTax)
                .must(value -> value != null)
                .withMessage("Additional financial operational tax must not be null")
                .withFieldName("additionalFinancialOperationalTax")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Additional financial operational tax must be greater than zero")
                .withFieldName("additionalFinancialOperationalTax");
    }
}

