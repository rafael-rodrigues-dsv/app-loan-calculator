package br.com.example;

import br.com.example.domain.calculator.enumeration.*;
import br.com.example.entrypoint.dto.request.*;
import br.com.example.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.example.entrypoint.util.ObjectMapperUtil;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class MainCalculatorLambdaTest {

    private static final ObjectMapper objectMapper = ObjectMapperUtil.createObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        MainCalculatorLambda lambda = new MainCalculatorLambda();

        // Simulate the input for the Lambda
        LoanCalculatorRequestDTO input = LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(PricingRequestDTO.builder()
                        .modalityType(ModalityTypeEnum.PRE_FIXADO)
                        .periodType(PeriodTypeEnum.MONTHLY)
                        .interestRate(BigDecimal.valueOf(1.75))
                        .build())
                .installmentQuantity(5)
                .amount(BigDecimal.valueOf(4_000))
                .contractDate(LocalDate.of(2025, Month.APRIL, 29))
                .firstInstallmentDate(LocalDate.of(2025, Month.MAY, 29))
                .fees(getFees())
                .insurances(getInsurances())
                .taxes(getTaxes())
                .build();

        // Simulate the Lambda context
        Context context = new Context() {
            @Override
            public String getAwsRequestId() {
                return "test-request-id";
            }

            @Override
            public String getLogGroupName() {
                return "test-log-group";
            }

            @Override
            public String getLogStreamName() {
                return "test-log-stream";
            }

            @Override
            public LambdaLogger getLogger() {
                return new LambdaLogger() {
                    @Override
                    public void log(String message) {
                        System.out.println("LOG: " + message);
                    }

                    @Override
                    public void log(byte[] message) {
                        System.out.println("LOG: " + new String(message));
                    }
                };
            }

            @Override
            public String getFunctionName() {
                return "LoanCalculatorLambda";
            }

            @Override
            public String getFunctionVersion() {
                return "1.0";
            }

            @Override
            public String getInvokedFunctionArn() {
                return "arn:aws:lambda:local:test";
            }

            @Override
            public CognitoIdentity getIdentity() {
                return null;
            }

            @Override
            public ClientContext getClientContext() {
                return null;
            }

            @Override
            public int getRemainingTimeInMillis() {
                return 300000;
            }

            @Override
            public int getMemoryLimitInMB() {
                return 512;
            }
        };

        // Execute the Lambda
        LoanCalculatorResponseDTO response = lambda.handleRequest(input, context);

        // Print the result
        context.getLogger().log("Response output: " + objectMapper.writeValueAsString(response));
        System.out.println("Response: " + response);

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
}