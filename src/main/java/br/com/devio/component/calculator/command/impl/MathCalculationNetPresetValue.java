package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.component.calculator.command.input.MathCalculationNetPresentValueInput;
import br.com.devio.component.calculator.command.input.MathCalculationPowerInput;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_YEAR;
import static br.com.devio.domain.constant.CalculationConstant.ROUNDING_MODE_HALF_UP;
import static br.com.devio.domain.constant.CalculationConstant.SCALE_10;

public class MathCalculationNetPresetValue implements MathCalculationCommand<MathCalculationNetPresentValueInput, BigDecimal> {

    private static final MathCalculationPow POWER_CALCULATOR = new MathCalculationPow();

    @Override
    public BigDecimal execute(MathCalculationNetPresentValueInput input) {
        List<InstalmentModel> installments = Optional.ofNullable(input)
                .map(MathCalculationNetPresentValueInput::getInstallments)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Installments list cannot be null or empty"));

        BigDecimal interestRate = Optional.ofNullable(input)
                .map(MathCalculationNetPresentValueInput::getInterestRate)
                .orElseThrow(() -> new IllegalArgumentException("Interest rate cannot be null"));

        BigDecimal totalNetPresentValue = BigDecimal.ZERO;

        for (InstalmentModel currentInstallment : installments) {
            BigDecimal currentInstallmentValue;

            if (currentInstallment.getInstallmentNumber() == 0) {
                currentInstallmentValue = currentInstallment.getTotalBalanceAmount() != null
                        ? currentInstallment.getTotalBalanceAmount().getAmount().negate()
                        : BigDecimal.ZERO;
            } else {
                currentInstallmentValue = currentInstallment.getTotalInstalmentValue() != null
                        ? currentInstallment.getTotalInstalmentValue().getAmount()
                        : BigDecimal.ZERO;
            }

            BigDecimal daysInYear = BigDecimal.valueOf(DAYS_IN_YEAR);
            BigDecimal contractDays = BigDecimal.valueOf(currentInstallment.getContractDays());

            BigDecimal exponent = contractDays.divide(daysInYear, SCALE_10, ROUNDING_MODE_HALF_UP);
            BigDecimal base = BigDecimal.ONE.add(interestRate);

            BigDecimal discountFactor = calculatePower(base, exponent);

            BigDecimal presentValue = currentInstallmentValue.divide(discountFactor, SCALE_10, ROUNDING_MODE_HALF_UP);
            totalNetPresentValue = totalNetPresentValue.add(presentValue);
        }

        return totalNetPresentValue;
    }

    private BigDecimal calculatePower(BigDecimal base, BigDecimal exponent) {
        return POWER_CALCULATOR.execute(MathCalculationPowerInput.builder()
                .base(base)
                .exponent(exponent)
                .build());
    }
}