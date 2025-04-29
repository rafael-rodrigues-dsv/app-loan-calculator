package br.com.example;

import br.com.example.domain.calculator.enumeration.*;
import br.com.example.domain.calculator.service.impl.CalculatorServiceImpl;
import br.com.example.entrypoint.command.ServiceCommand;
import br.com.example.entrypoint.command.calculator.CreateLoanCalculatorCommand;
import br.com.example.entrypoint.dto.request.*;
import br.com.example.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.example.entrypoint.mapper.impl.CustomMapperImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO>
            calculatorCommand = new CreateLoanCalculatorCommand(new CalculatorServiceImpl(), new CustomMapperImpl());

    private static ObjectMapper objectMapper = createObjectMapper();

    public static void main(String[] args) {
        try {
            LoanCalculatorResponseDTO response = calculatorCommand.execute(getLoanPre());
            String jsonResponse = objectMapper.writeValueAsString(response);
            System.out.println("Payment plan calculated: " + jsonResponse);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting response to JSON: " + e.getMessage());
        }
    }

    private static LoanCalculatorRequestDTO getLoanPre() {
        PricingRequestDTO pricingModel = PricingRequestDTO.builder()
                .modalityType(ModalityTypeEnum.PRE_FIXADO)
                .periodType(PeriodTypeEnum.MONTHLY)
                .interestRate(BigDecimal.valueOf(1.75))
                .build();

        return LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricingModel)
                .installmentQuantity(5)
                .amount(BigDecimal.valueOf(4_000))
                .contractDate(LocalDate.of(2025, Month.APRIL, 29))
                .firstInstallmentDate(LocalDate.of(2025, Month.MAY, 29))
                .fees(getFees())
                .insurances(getInsurances())
                .taxes(getTaxes())
                .build();
    }

    private static LoanCalculatorRequestDTO getLoanPos() {
        PricingRequestDTO pricingModel = PricingRequestDTO.builder()
                .modalityType(ModalityTypeEnum.POS_FIXADO)
                .periodType(PeriodTypeEnum.MONTHLY)
                .interestRate(BigDecimal.valueOf(1.75))
                .benchmarks(getBenchmarks())
                .build();

        return LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricingModel)
                .installmentQuantity(5)
                .amount(BigDecimal.valueOf(5_000))
                .contractDate(LocalDate.of(2025, Month.APRIL, 29))
                .firstInstallmentDate(LocalDate.of(2025, Month.MAY, 29))
                .fees(getFees())
                .insurances(getInsurances())
                .taxes(getTaxes())
                .build();
    }

    private static List<BenchmarkRequestDTO> getBenchmarks() {
        List<BenchmarkRequestDTO> benchmarks = new ArrayList<>();

        benchmarks.add(BenchmarkRequestDTO.builder()
                .name("CDI")
                .periodType(PeriodTypeEnum.YEARLY)
                .interestRate(BigDecimal.valueOf(13.15))
                .interestRateTotalComposition(BigDecimal.valueOf(100.00))
                .build());

        return benchmarks;
    }

    private static List<FeeRequestDTO> getFees() {
        List<FeeRequestDTO> fees = new ArrayList<>();

        fees.add(FeeRequestDTO.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(200.00))
                .build());

        return fees;
    }

    private static List<InsuranceRequestDTO> getInsurances() {
        List<InsuranceRequestDTO> insurances = new ArrayList<>();

        insurances.add(InsuranceRequestDTO.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(800.00))
                .build());

        return insurances;
    }

    private static List<TaxRequestDTO> getTaxes() {
        List<TaxRequestDTO> taxes = new ArrayList<>();

        taxes.add(TaxRequestDTO.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(0.0041))
                .taxType(TaxTypeEnum.IOF_DIA)
                .build());

        taxes.add(TaxRequestDTO.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(0.3800))
                .taxType(TaxTypeEnum.IOF_ADICIONAL)
                .build());

        return taxes;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}