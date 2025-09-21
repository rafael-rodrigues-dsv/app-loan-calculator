package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
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
        BigDecimal totalAmortizationAmount = currentInstallment.getTotalInstalmentValue()
                .subtract(currentInstallment.getTotalInterestAmount())
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalAmortizationAmount(totalAmortizationAmount);
        return currentInstallment;
    }
}

