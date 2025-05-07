package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.model.FeeModel;
import br.com.devio.component.domain.model.FinancialOperationalTaxModel;
import br.com.devio.component.domain.model.InsuranceModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.Objects;


public class CalculationTotalFinancedAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        final InsuranceModel insurance = paymentPlanModel.getInsurance();
        final FeeModel fee = paymentPlanModel.getFee();
        final FinancialOperationalTaxModel financialOperationalTax = paymentPlanModel.getFinancialOperationalTax();

        double totalInsurance =
                Objects.nonNull(insurance)
                        && Objects.nonNull(insurance.getTotalValue())
                        && Objects.nonNull(insurance.getPaymentType())
                        && insurance.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? paymentPlanModel.getInsurance().getTotalValue().doubleValue()
                        : 0;

        double totalFee =
                Objects.nonNull(fee)
                        && Objects.nonNull(fee.getTotalValue())
                        && Objects.nonNull(fee.getPaymentType())
                        && fee.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? paymentPlanModel.getFee().getTotalValue().doubleValue()
                        : 0;

        double totalFinancialOperationalTax =
                Objects.nonNull(financialOperationalTax)
                        && Objects.nonNull(financialOperationalTax.getTotalValue())
                        && Objects.nonNull(financialOperationalTax.getPaymentType())
                        && financialOperationalTax.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? paymentPlanModel.getFinancialOperationalTax().getTotalValue().doubleValue()
                        : 0;

        paymentPlanModel.setTotalFinancedAmount(
                BigDecimal.valueOf(paymentPlanModel.getAmount().doubleValue()
                                + totalFee
                                + totalInsurance
                                + totalFinancialOperationalTax)
                        .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
