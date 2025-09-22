package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.enumeration.PaymentTypeEnum;
import br.com.devio.domain.model.*;

import java.math.BigDecimal;
import java.util.Optional;

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
        BigDecimal totalInsuranceAmount = Optional.ofNullable(paymentPlanModel.getInsurance())
                .filter(i -> PaymentTypeEnum.FINANCED.equals(i.getPaymentType()))
                .map(InsuranceModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalFeeAmount = Optional.ofNullable(paymentPlanModel.getFee())
                .filter(f -> PaymentTypeEnum.FINANCED.equals(f.getPaymentType()))
                .map(FeeModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalTaxAmount = Optional.ofNullable(paymentPlanModel.getTax())
                .filter(t -> PaymentTypeEnum.FINANCED.equals(t.getPaymentType()))
                .map(TaxModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal requestedAmount = Optional.ofNullable(paymentPlanModel.getRequestedAmount())
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalFinanced = requestedAmount
                .add(totalFeeAmount)
                .add(totalInsuranceAmount)
                .add(totalTaxAmount)
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        paymentPlanModel.setTotalFinancedAmount(AmountModel.builder()
                .amount(totalFinanced)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return paymentPlanModel;
    }
}
