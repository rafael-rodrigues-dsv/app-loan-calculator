package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.model.InstallmentModel;
import br.com.devio.component.domain.model.PaymentPlanModel;
import br.com.devio.component.domain.model.TaxModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.devio.component.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

public class CalculationPriceInstallment extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        List<InstallmentModel> installments = new ArrayList<>();
        final BigDecimal totalFinancedAmount = paymentPlanModel.getTotalFinancedAmount();
        final Integer installmentQuantity = paymentPlanModel.getInstallmentQuantity();
        final TaxModel dailyFinancialOperationalTax = Objects.nonNull(paymentPlanModel.getTaxes()) && !paymentPlanModel.getTaxes().isEmpty()
                ? paymentPlanModel.getTaxes().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCED) && f.getTaxType().equals(TaxTypeEnum.DAILY_IOF))
                .findFirst()
                .get()
                : null;
        final TaxModel additionalFinancialOperationalTax = Objects.nonNull(paymentPlanModel.getTaxes()) && !paymentPlanModel.getTaxes().isEmpty()
                ? paymentPlanModel.getTaxes().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCED) && f.getTaxType().equals(TaxTypeEnum.ADDITIONAL_IOF))
                .findFirst()
                .get()
                : null;

        paymentPlanModel.getInstallments().forEach(currentInstallment -> {
            if (currentInstallment.getNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalPresentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(currentInstallment, currentInstallment);
            } else {
                InstallmentModel beforeInstallment = installments.get(currentInstallment.getNumber() - 1);

                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalPresentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalInterestAmount())
                        .add(new CalculationPriceInstallmentTotalInstallmentValue())
                        .add(new CalculationPriceInstallmentTotalAmortizationAmount())
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalDailyFinancialOperationalTax(dailyFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalAdditionalFinancialOperationalTax(additionalFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalFinancialOperationalTax())
                        .build();

                currentInstallment = chain.calculate(beforeInstallment, currentInstallment);
            }

            installments.add(currentInstallment);
        });

        paymentPlanModel.setInstallments(installments
                .stream()
                .filter(f -> f.getNumber() != INSTALLMENT_NUMBER_INITIAL)
                .toList());

        return paymentPlanModel;
    }
}
