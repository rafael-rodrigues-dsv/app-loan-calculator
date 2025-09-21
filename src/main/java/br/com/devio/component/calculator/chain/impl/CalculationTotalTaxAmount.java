package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.PaymentPlanModel;
import br.com.devio.domain.model.TaxModel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

/**
 * 📄 Consolidador do valor total de impostos
 */
public class CalculationTotalTaxAmount extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * Total Impostos = IOFTotal    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * IOFTotal = IOF total da última parcela calculada
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: Última parcela IOF = 392,30 → Total = R$ 392,30
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        TaxModel tax = paymentPlanModel.getTax();

        if (Objects.nonNull(tax)) {
            double totalFinancialOperationalTax = paymentPlanModel.getInstallments() != null && !paymentPlanModel.getInstallments().isEmpty()
                    ? paymentPlanModel.getInstallments().stream()
                    .filter(f -> f.getInstallmentNumber() != INSTALLMENT_NUMBER_INITIAL && Objects.nonNull(f.getTotalFinancialOperationalTax()))
                    .reduce((first, second) -> second) // Obtém a última parcela
                    .map(last -> Optional.ofNullable(last.getTotalFinancialOperationalTax())
                            .map(AmountModel::getAmount)
                            .map(BigDecimal::doubleValue)
                            .orElse(0.0))
                    .orElse(0.0)
                    : 0;

            tax.setTotalAmount(AmountModel.builder()
                    .amount(BigDecimal.valueOf(totalFinancialOperationalTax)
                            .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE))
                    .currency("BRL")
                    .build());

            paymentPlanModel.setTax(tax);
        }

        return paymentPlanModel;
    }
}
