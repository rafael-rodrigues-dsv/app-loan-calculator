package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.domain.model.InstallmentModel;

import java.util.List;

import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_YEAR;

public class MathCalculationNetPresentValue implements MathCalculationCommand<List<InstallmentModel>, Double> {

    private final Double interestRate;

    public MathCalculationNetPresentValue(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Double execute(List<InstallmentModel> installments) {
        Double totalNetPresentValue = 0.0;

        for (InstallmentModel currentInstallment : installments) {
            Double currentInstallmentValue = currentInstallment.getTotalInstalmentValue() != null
                    ? currentInstallment.getTotalInstalmentValue().doubleValue()
                    : 0.0;

            if (currentInstallment.getNumber() == 0) {
                currentInstallmentValue = currentInstallment.getTotalBalanceAmount() != null
                        ? currentInstallment.getTotalBalanceAmount().negate().doubleValue()
                        : 0.0;
            }

            Double discountFactor = Math.pow(
                    1 + interestRate,
                    currentInstallment.getContractDays() / DAYS_IN_YEAR);

            totalNetPresentValue += currentInstallmentValue / discountFactor;
        }

        return totalNetPresentValue;
    }
}
