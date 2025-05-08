package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.model.FeeModel;
import br.com.devio.component.domain.model.InsuranceModel;
import br.com.devio.component.domain.model.PaymentPlanModel;
import br.com.devio.component.domain.model.TaxModel;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationTotalGrantedAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        final InsuranceModel insurance = paymentPlanModel.getInsurance();
        final FeeModel fee = paymentPlanModel.getFee();
        final TaxModel tax = paymentPlanModel.getTax();

        double totalInsuranceAmount =
                Objects.nonNull(insurance)
                        && Objects.nonNull(insurance.getTotalAmount())
                        && Objects.nonNull(insurance.getPaymentType())
                        && insurance.getPaymentType().equals(PaymentTypeEnum.UPFRONT)
                        ? insurance.getTotalAmount().doubleValue()
                        : 0;

        double totalFeeAmount =
                Objects.nonNull(fee)
                        && Objects.nonNull(fee.getTotalAmount())
                        && Objects.nonNull(fee.getPaymentType())
                        && fee.getPaymentType().equals(PaymentTypeEnum.UPFRONT)
                        ? fee.getTotalAmount().doubleValue()
                        : 0;

        double totalTaxAmount =
                Objects.nonNull(tax)
                        && Objects.nonNull(tax.getTotalAmount())
                        && Objects.nonNull(tax.getPaymentType())
                        && tax.getPaymentType().equals(PaymentTypeEnum.UPFRONT)
                        ? tax.getTotalAmount().doubleValue()
                        : 0;

        paymentPlanModel.setTotalGrantedAmount(
                BigDecimal.valueOf(paymentPlanModel.getRequestedAmount().doubleValue()
                                - totalFeeAmount
                                - totalInsuranceAmount
                                - totalTaxAmount)
                        .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
