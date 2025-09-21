package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.enumeration.PaymentTypeEnum;
import br.com.devio.domain.model.FeeModel;
import br.com.devio.domain.model.TaxModel;
import br.com.devio.domain.model.InsuranceModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 💵 Calculadora do valor total financiado
 */
public class CalculationTotalFinancedAmount extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * TF = VS + Σ(SF) + Σ(TF) + Σ(IF)    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * Σ(SF) = soma de todos os seguros financiados
     * Σ(TF) = soma de todas as tarifas financiadas
     * Σ(IF) = soma de todos os impostos financiados
     * TF = Total Financiado
     * VS = Valor Solicitado
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 50.000 + 1.500 + 0 + 500 = R$ 52.000
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        final InsuranceModel insurance = paymentPlanModel.getInsurance();
        final FeeModel fee = paymentPlanModel.getFee();
        final TaxModel tax = paymentPlanModel.getTax();

        double totalInsuranceAmount =
                Objects.nonNull(insurance)
                        && Objects.nonNull(insurance.getTotalAmount())
                        && Objects.nonNull(insurance.getPaymentType())
                        && insurance.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? insurance.getTotalAmount().doubleValue()
                        : 0;

        double totalFeeAmount =
                Objects.nonNull(fee)
                        && Objects.nonNull(fee.getTotalAmount())
                        && Objects.nonNull(fee.getPaymentType())
                        && fee.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? fee.getTotalAmount().doubleValue()
                        : 0;

        double totalTaxAmount =
                Objects.nonNull(tax)
                        && Objects.nonNull(tax.getTotalAmount())
                        && Objects.nonNull(tax.getPaymentType())
                        && tax.getPaymentType().equals(PaymentTypeEnum.FINANCED)
                        ? tax.getTotalAmount().doubleValue()
                        : 0;

        paymentPlanModel.setTotalFinancedAmount(
                BigDecimal.valueOf(paymentPlanModel.getRequestedAmount().doubleValue()
                                + totalFeeAmount
                                + totalInsuranceAmount
                                + totalTaxAmount)
                        .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
