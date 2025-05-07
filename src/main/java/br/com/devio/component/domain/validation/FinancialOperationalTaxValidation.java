package br.com.devio.component.domain.validation;

import br.com.devio.component.domain.model.FinancialOperationalTaxModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class FinancialOperationalTaxValidation extends AbstractValidator<FinancialOperationalTaxModel> {

    @Override
    public void rules() {
        ruleFor(FinancialOperationalTaxModel::getPaymentType)
                .must(not(nullValue()))
                .withMessage("Payment type cannot be null")
                .withFieldName("paymentType");

        ruleFor(FinancialOperationalTaxModel::getDailyFinancialOperationalTax)
                .must(value -> value != null)
                .withMessage("Daily financial operational tax must not be null")
                .withFieldName("dailyFinancialOperationalTax")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Daily financial operational tax Value must be greater than zero")
                .withFieldName("dailyFinancialOperationalTax");

        ruleFor(FinancialOperationalTaxModel::getAdditionalFinancialOperationalTax)
                .must(value -> value != null)
                .withMessage("Additional financial operational tax must not be null")
                .withFieldName("additionalFinancialOperationalTax")
                .must(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Additional financial operational tax must be greater than zero")
                .withFieldName("additionalFinancialOperationalTax");
    }
}

