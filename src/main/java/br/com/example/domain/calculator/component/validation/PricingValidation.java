package br.com.example.domain.calculator.component.validation;

import br.com.example.domain.calculator.enumeration.ModalityTypeEnum;
import br.com.example.domain.calculator.model.PricingModel;
import br.com.fluentvalidator.AbstractValidator;

import java.util.List;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

public class PricingValidation extends AbstractValidator<PricingModel> {

    private static final List<ModalityTypeEnum> ALLOWED_CALCULATION_TYPES = List.of(ModalityTypeEnum.PRE_FIXADO);

    @Override
    public void rules() {
        ruleFor(PricingModel::getModalityType)
                .must(not(nullValue()))
                .withMessage("Modality type cannot be null")
                .withFieldName("modalityType")
                .must(ALLOWED_CALCULATION_TYPES::contains)
                .withMessage(modalityType -> String.format("The provided modality type '%s' is not available", modalityType.getModalityType().name()))
                .withFieldName("modalityType");

        ruleFor(PricingModel::getPeriodType)
                .must(not(nullValue()))
                .withMessage("Period type cannot be null")
                .withFieldName("periodType");

        ruleFor(PricingModel::getInterestRate)
                .must(not(nullValue()))
                .withMessage("Interest rate cannot be null")
                .withFieldName("interestRate")
                .must(rate -> rate.doubleValue() > 0)
                .withMessage("Interest rate must be greater than zero");
    }
}
