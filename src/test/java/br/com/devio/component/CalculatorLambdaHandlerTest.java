package br.com.devio.component;

import br.com.devio.component.configuration.ObjectMapperConfig;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.devio.component.context.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculatorLambdaHandlerTest {

    private final CalculatorLambdaHandler lambda = new CalculatorLambdaHandler();
    private final ObjectMapper objectMapper = ObjectMapperConfig.getObjectMapper();

    @Test
    void testHandleRequest_withValidInput_shouldReturnValidResponse() throws Exception {
        String input = "{\"calculationType\":\"PRICE\",\"pricing\":{\"modalityType\":\"PRE_FIXADO\",\"periodType\":\"MONTHLY\",\"interestRate\":1.75},\"installmentQuantity\":5,\"amount\":4000,\"contractDate\":\"2025-04-29\",\"firstInstallmentDate\":\"2025-05-29\",\"fees\":[{\"paymentType\":\"FINANCIADO\",\"value\":200.0}],\"insurances\":[{\"paymentType\":\"FINANCIADO\",\"value\":800.0}],\"taxes\":[{\"paymentType\":\"FINANCIADO\",\"value\":0.0041,\"taxType\":\"IOF_DIA\"},{\"paymentType\":\"FINANCIADO\",\"value\":0.38,\"taxType\":\"IOF_ADICIONAL\"}]}";
        LoanCalculatorResponseDTO response = objectMapper.readValue(lambda.handleRequest(input, new TestContext()), LoanCalculatorResponseDTO.class);
        assertNotNull(response);
        assertNotNull(response.getInstallments());
        assertFalse(response.getInstallments().isEmpty());
        assertEquals(5, response.getInstallments().size());
    }
}
