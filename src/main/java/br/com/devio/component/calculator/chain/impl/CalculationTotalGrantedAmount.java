package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.enumeration.PaymentTypeEnum;
import br.com.devio.domain.model.*;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 💳 Calculadora do valor total concedido
 */
public class CalculationTotalGrantedAmount extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * TC = VS - Σ(SV) - Σ(TV) - Σ(IV)    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * Σ(SV) = soma de todos os seguros à vista
     * Σ(TV) = soma de todas as tarifas à vista
     * Σ(IV) = soma de todos os impostos à vista
     * TC = Total Concedido (líquido na conta)
     * VS = Valor Solicitado
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 50.000 - 0 - 200 - 500 = R$ 49.300
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        final InsuranceModel insurance = paymentPlanModel.getInsurance();
        final FeeModel fee = paymentPlanModel.getFee();
        final TaxModel tax = paymentPlanModel.getTax();

        BigDecimal totalInsuranceAmount = Optional.ofNullable(insurance)
                .filter(i -> PaymentTypeEnum.UPFRONT.equals(i.getPaymentType()))
                .map(InsuranceModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalFeeAmount = Optional.ofNullable(fee)
                .filter(f -> PaymentTypeEnum.UPFRONT.equals(f.getPaymentType()))
                .map(FeeModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalTaxAmount = Optional.ofNullable(tax)
                .filter(t -> PaymentTypeEnum.UPFRONT.equals(t.getPaymentType()))
                .map(TaxModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal requestedAmount = Optional.ofNullable(paymentPlanModel.getRequestedAmount())
                .map(AmountModel::getAmount)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalGranted = requestedAmount
                .subtract(totalFeeAmount)
                .subtract(totalInsuranceAmount)
                .subtract(totalTaxAmount)
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        paymentPlanModel.setTotalGrantedAmount(AmountModel.builder()
                .amount(totalGranted)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return paymentPlanModel;
    }
}
