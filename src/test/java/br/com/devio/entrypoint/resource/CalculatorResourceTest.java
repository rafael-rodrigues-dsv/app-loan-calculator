package br.com.devio.entrypoint.resource;

import br.com.devio.generated.dto.*;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CalculatorResourceTest {

    @Test
    void testCalculateLoan_withValidInput_shouldReturnValidResponse() throws Exception {
        SimulationRequestDTO request = SimulationRequestDTO.builder()
                .calculationType(CalculationType.PRICE)
                .installmentQuantity(5)
                .requestedAmount(AmountRequestDTO.builder()
                        .amount(BigDecimal.valueOf(4000.0))
                        .currency("BRL")
                        .build())
                .contractDate(LocalDate.of(2025, 4, 29))
                .firstInstallmentDate(LocalDate.of(2025, 5, 29))
                .monthlyInterestRate(BigDecimal.valueOf(0.0175))
                .fee(FeeRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .totalAmount(AmountRequestDTO.builder()
                                .amount(BigDecimal.valueOf(200.0))
                                .currency("BRL")
                                .build())
                        .build())
                .insurance((InsuranceRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .totalAmount(AmountRequestDTO.builder()
                                .amount(BigDecimal.valueOf(800.0))
                                .currency("BRL")
                                .build())
                        .build()))
                .tax(TaxRequestDTO.builder()
                        .paymentType(PaymentType.FINANCED)
                        .dailyFinancialOperationalTax(AmountRequestDTO.builder()
                                .amount(BigDecimal.valueOf(0.0082))
                                .currency("BRL")
                                .build())
                        .additionalFinancialOperationalTax(AmountRequestDTO.builder()
                                .amount(BigDecimal.valueOf(0.38))
                                .currency("BRL")
                                .build())
                        .build())
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/simulations")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        assertNotNull(response.getBody().asString());
    }

    @Test
    void testCalculateLoan_withInvalidInput_shouldReturnBadRequest() {
        SimulationRequestDTO request = SimulationRequestDTO.builder()
                .calculationType(CalculationType.PRICE)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/simulations")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
