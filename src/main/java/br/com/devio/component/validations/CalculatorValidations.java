package br.com.devio.component.validations;

import br.com.devio.domain.model.LoanModel;
import br.com.fluentvalidator.AbstractValidator;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class CalculatorValidations extends AbstractValidator<LoanModel> {
    @Override
    public void rules() {
        ruleFor(LoanModel::getCalculationType)
                .must(not(nullValue()))
                .withMessage("Calculation type cannot be null")
                .withFieldName("calculationType");

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

        ruleFor(LoanModel::getRequestedAmount)
                .must(not(nullValue()))
                .withMessage("Requested amount cannot be null")
                .withFieldName("requestedAmount")
                .must(amountModel -> Objects.nonNull(amountModel) && Objects.nonNull(amountModel.getAmount()) && amountModel.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Requested amount must be greater than zero")
                .withFieldName("requestedAmount");

        ruleFor(LoanModel::getContractDate)
                .must(not(nullValue()))
                .withMessage("Contract date cannot be null")
                .withFieldName("contractDate");

        ruleFor(LoanModel::getFirstInstallmentDate)
                .must(not(nullValue()))
                .withMessage("First installment date cannot be null")
                .withFieldName("firstInstallmentDate");

        ruleFor(LoanModel::getMonthlyInterestRate)
                .must(not(nullValue()))
                .withMessage("Monthly interest rate cannot be null")
                .withFieldName("monthlyInterestRate")
                .must(rate -> Objects.nonNull(rate) && rate.compareTo(BigDecimal.ZERO) > 0)
                .withMessage("Monthly interest rate must be greater than zero")
                .withFieldName("monthlyInterestRate");

        ruleFor(LoanModel::getFee)
                .whenever(not(nullValue()))
                .withValidator(new FeeValidations());

        ruleFor(LoanModel::getInsurance)
                .whenever(not(nullValue()))
                .withValidator(new InsuranceValidations());

        ruleFor(LoanModel::getTax)
                .whenever(not(nullValue()))
                .withValidator(new TaxValidations());
    }
}
