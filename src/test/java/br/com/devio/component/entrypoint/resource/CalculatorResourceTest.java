package br.com.devio.component.entrypoint.resource;

import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.entrypoint.dto.request.FeeRequestDTO;
import br.com.devio.component.entrypoint.dto.request.TaxRequestDTO;
import br.com.devio.component.entrypoint.dto.request.InsuranceRequestDTO;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CalculatorResourceTest {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

    @Test
    void testCalculateLoan_withValidInput_shouldReturnValidResponse() throws Exception {
        // Usando Lombok Builder para construir o request
        LoanCalculatorRequestDTO request = LoanCalculatorRequestDTO.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .installmentQuantity(5)
                .requestedAmount(BigDecimal.valueOf(4000.0))
                .contractDate(LocalDate.of(2025, 4, 29))
                .firstInstallmentDate(LocalDate.of(2025, 5, 29))
                .monthlyInterestRate(BigDecimal.valueOf(1.75))
                .fee(FeeRequestDTO.builder()
                        .paymentType(PaymentTypeEnum.FINANCED)
                        .totalAmount(BigDecimal.valueOf(200.0))
                        .build())
                .insurance((InsuranceRequestDTO.builder()
                        .paymentType(PaymentTypeEnum.FINANCED)
                        .totalAmount(BigDecimal.valueOf(800.0))
                        .build()))
                .tax(TaxRequestDTO.builder()
                        .paymentType(PaymentTypeEnum.FINANCED)
                        .dailyFinancialOperationalTax(BigDecimal.valueOf(0.0082))
                        .additionalFinancialOperationalTax(BigDecimal.valueOf(0.38))
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

        // Validando a resposta
        assertNotNull(response);
        LoanCalculatorResponseDTO responseDTO = objectMapper.readValue(response.getBody().asString(), LoanCalculatorResponseDTO.class);
        assertNotNull(responseDTO);
    }

    @Test
    void testCalculateLoan_withInvalidInput_shouldReturnBadRequest() {
        LoanCalculatorRequestDTO request = LoanCalculatorRequestDTO.builder()
                .build(); // Dados incompletos ou inválidos

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/calculator")
                .then()
                .statusCode(400)
                .extract()
                .response();

        assertNotNull(response);
        // Validação dos erros de validação ou retorno esperado
        assertFalse(response.getBody().asString().isEmpty());
    }
}
