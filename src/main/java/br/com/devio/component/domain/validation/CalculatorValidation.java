package br.com.devio.component.domain.validation;

import br.com.devio.component.domain.model.LoanModel;
import br.com.fluentvalidator.AbstractValidator;

import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class CalculatorValidation extends AbstractValidator<LoanModel> {
    @Override
    public void rules() {
        ruleFor(LoanModel::getCalculationType)
                .must(not(nullValue()))
                .withMessage("Calculation type cannot be null")
                .withFieldName("calculationType");

        ruleFor(LoanModel::getPricing)
                .must(not(nullValue()))
                .withMessage("Pricing model cannot be null")
                .withFieldName("pricing")
                .whenever(not(nullValue()))
                .withValidator(new PricingValidation());

        ruleFor(LoanModel::getInstallmentQuantity)
                .must(not(nullValue()))
                .withMessage("Installment quantity cannot be null")
                .withFieldName("installmentQuantity")
                .must(quantity -> Objects.nonNull(quantity) && quantity > 0)
                .withMessage("Installment quantity must be greater than zero")
                .withFieldName("installmentQuantity")
                .must(quantity -> Objects.nonNull(quantity) && quantity < 360)
                .withMessage("Installment quantity must be less than 360")
                .withFieldName("installmentQuantity");

        ruleFor(LoanModel::getAmount)
                .must(not(nullValue()))
                .withMessage("Amount cannot be null")
                .withFieldName("amount")
                .must(amount -> Objects.nonNull(amount) && amount.doubleValue() > 0)
                .withMessage("Amount must be greater than zero")
                .withFieldName("amount");

        ruleFor(LoanModel::getContractDate)
                .must(not(nullValue()))
                .withMessage("Contract date cannot be null")
                .withFieldName("contractDate");

        ruleFor(LoanModel::getFirstInstallmentDate)
                .must(not(nullValue()))
                .withMessage("First installment date cannot be null")
                .withFieldName("firstInstallmentDate");

        ruleForEach(LoanModel::getFees)
                .whenever(not(nullValue()))
                .withValidator(new FeeValidation());

        ruleForEach(LoanModel::getInsurances)
                .whenever(not(nullValue()))
                .withValidator(new InsuranceValidation());

        ruleForEach(LoanModel::getTaxes)
                .whenever(not(nullValue()))
                .withValidator(new TaxValidation());
    }
}
