package br.com.example;

import br.com.example.domain.calculator.enumeration.*;
import br.com.example.domain.calculator.model.*;
import br.com.example.domain.calculator.service.calculator.impl.CalculatorServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PaymentPlanModel paymentPlanModel = new CalculatorServiceImpl()
                .calculate(getLoanPre());

        System.out.println("Payment plan calculated!");
    }

    private static LoanModel getLoanPre() {
        PricingModel pricingModel = PricingModel.builder()
                .modalityType(ModalityTypeEnum.PRE_FIXADO)
                .periodType(PeriodTypeEnum.MES)
                .interestRate(BigDecimal.valueOf(1.75))
                .build();

        return LoanModel.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricingModel)
                .installmentQuantity(5)
                .amount(BigDecimal.valueOf(5.000))
                .contractDate(LocalDate.of(2023, Month.AUGUST, 21))
                .firstInstallmentDate(LocalDate.of(2023, Month.SEPTEMBER, 21))
                .fees(getFees())
                .insurances(getInsurances())
                .taxes(getTaxes())
                .build();
    }

    private static LoanModel getLoanPos() {
        PricingModel pricingModel = PricingModel.builder()
                .modalityType(ModalityTypeEnum.POS_FIXADO)
                .periodType(PeriodTypeEnum.MES)
                .interestRate(BigDecimal.valueOf(1.75))
                .benchmarks(getBenchmarks())
                .build();

        return LoanModel.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricingModel)
                .installmentQuantity(5)
                .amount(BigDecimal.valueOf(5.000))
                .contractDate(LocalDate.of(2023, Month.AUGUST, 21))
                .firstInstallmentDate(LocalDate.of(2023, Month.SEPTEMBER, 21))
                .fees(getFees())
                .insurances(getInsurances())
                .taxes(getTaxes())
                .build();
    }

    private static List<BenchmarkModel> getBenchmarks() {
        List<BenchmarkModel> benchmarks = new ArrayList<>();

        benchmarks.add(BenchmarkModel.builder()
                .name("CDI")
                .periodType(PeriodTypeEnum.ANO)
                .interestRate(BigDecimal.valueOf(13.15))
                .interestRateTotalComposition(BigDecimal.valueOf(100.00))
                .build());

        return benchmarks;
    }

    private static List<FeeModel> getFees() {
        List<FeeModel> fees = new ArrayList<>();

        fees.add(FeeModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(94.99))
                .build());

        return fees;
    }

    private static List<InsuranceModel> getInsurances() {
        List<InsuranceModel> insurances = new ArrayList<>();

        insurances.add(InsuranceModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(37.76))
                .build());

        return insurances;
    }

    private static List<TaxModel> getTaxes() {
        List<TaxModel> taxes = new ArrayList<>();

        taxes.add(TaxModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(0.0041))
                .taxType(TaxTypeEnum.IOF_DIA)
                .build());

        taxes.add(TaxModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(0.3800))
                .taxType(TaxTypeEnum.IOF_ADICIONAL)
                .build());

        return taxes;
    }
}