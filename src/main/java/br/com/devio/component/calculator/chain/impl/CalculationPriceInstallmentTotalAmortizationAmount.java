package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

/**
 * 💰 Calculadora de amortização - sistema PRICE
 */
public class CalculationPriceInstallmentTotalAmortizationAmount extends CalculatorEngine<InstallmentModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * A(i) = PMT - J(i)    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * A(i) = Amortização da parcela i
     * PMT = Valor fixo da parcela
     * J(i) = Juros da parcela i
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 2.625,22 - 1.300,00 = R$ 1.325,22
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        if (beforeInstallment == null || currentInstallment == null) {
            throw new IllegalArgumentException("Installment parameters cannot be null");
        }
        if (currentInstallment.getTotalInstalmentValue() == null || currentInstallment.getTotalInterestAmount() == null) {
            throw new IllegalArgumentException("Required installment values cannot be null");
        }
        
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue().getAmount()
                .subtract(currentInstallment.getTotalInterestAmount().getAmount())
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalAmortizationAmount(AmountModel.builder()
                .amount(totalAmortizationAmount)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());
        return currentInstallment;
    }
}

