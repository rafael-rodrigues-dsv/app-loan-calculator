package br.com.devio.entrypoint.resource;

import br.com.devio.component.utils.ObjectMapperUtils;
import br.com.devio.generated.dto.*;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CalculatorResourceTest {

    @Test
    void testCalculateLoan_withValidInput_shouldReturnValidResponse() throws Exception {
        // Usando Lombok Builder para construir o request
        LoanCalculatorRequestDTO request = LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationType.PRICE)
                .installmentQuantity(5)
                .requestedAmount(AmountDTO.builder()
                        .amount(BigDecimal.valueOf(4000.0))
                        .currency("BRL")
                        .build())
                .contractDate(LocalDate.of(2025, 4, 29))
                .firstInstallmentDate(LocalDate.of(2025, 5, 29))
                .monthlyInterestRate(BigDecimal.valueOf(0.0175))
                .fee(FeeRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .totalAmount(AmountDTO.builder()
                                .amount(BigDecimal.valueOf(200.0))
                                .currency("BRL")
                                .build())
                        .build())
                .insurance((InsuranceRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .totalAmount(AmountDTO.builder()
                                .amount(BigDecimal.valueOf(800.0))
                                .currency("BRL")
                                .build())
                        .build()))
                .tax(TaxRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .dailyFinancialOperationalTax(AmountDTO.builder()
                                .amount(BigDecimal.valueOf(0.0082))
                                .currency("BRL")
                                .build())
                        .additionalFinancialOperationalTax(AmountDTO.builder()
                                .amount(BigDecimal.valueOf(0.38))
                                .currency("BRL")
                                .build())
                        .build())
                .build();

        // Fazendo a requisição
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/calculator")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        LoanCalculatorResponseDTO responseDTO = ObjectMapperUtils.getObjectMapper().readValue(responseBody, LoanCalculatorResponseDTO.class);
        assertNotNull(responseDTO);
    }

    @Test
    void testCalculateLoan_withInvalidInput_shouldReturnBadRequest() {
        // Request com dados obrigatórios faltando
        LoanCalculatorRequestDTO request = LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationType.PRICE)
                // Faltando campos obrigatórios
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/calculator")
                .then()
                .statusCode(400);
    }
}
