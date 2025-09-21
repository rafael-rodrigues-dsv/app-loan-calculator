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

        double totalInsuranceAmount = Optional.ofNullable(insurance)
                .filter(i -> PaymentTypeEnum.UPFRONT.equals(i.getPaymentType()))
                .map(InsuranceModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .map(BigDecimal::doubleValue)
                .orElse(0.0);

        double totalFeeAmount = Optional.ofNullable(fee)
                .filter(f -> PaymentTypeEnum.UPFRONT.equals(f.getPaymentType()))
                .map(FeeModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .map(BigDecimal::doubleValue)
                .orElse(0.0);

        double totalTaxAmount = Optional.ofNullable(tax)
                .filter(t -> PaymentTypeEnum.UPFRONT.equals(t.getPaymentType()))
                .map(TaxModel::getTotalAmount)
                .map(AmountModel::getAmount)
                .map(BigDecimal::doubleValue)
                .orElse(0.0);

        double requestedAmountValue = Optional.ofNullable(paymentPlanModel.getRequestedAmount())
                .map(AmountModel::getAmount)
                .map(BigDecimal::doubleValue)
                .orElse(0.0);

        paymentPlanModel.setTotalGrantedAmount(AmountModel.builder()
                .amount(BigDecimal.valueOf(requestedAmountValue
                        - totalFeeAmount
                        - totalInsuranceAmount
                        - totalTaxAmount)
                        .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE))
                .currency("BRL")
                .build());

        return paymentPlanModel;
    }
}
