package br.com.devio.component;

import br.com.devio.component.configuration.ObjectMapperConfig;
import br.com.devio.component.domain.calculator.service.impl.CalculatorServiceImpl;
import br.com.devio.component.domain.exception.CalculatorValidationException;
import br.com.devio.component.entrypoint.command.ServiceCommand;
import br.com.devio.component.entrypoint.command.calculator.CreateLoanCalculatorCommand;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.devio.component.entrypoint.mapper.impl.CustomMapperImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class CalculatorLambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private ObjectMapper objectMapper = ObjectMapperConfig.getObjectMapper();

    private static final ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand =
            new CreateLoanCalculatorCommand(new CalculatorServiceImpl(), new CustomMapperImpl());

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);

        try {
            context.getLogger().log("Received input: " + input.getBody());
            LoanCalculatorRequestDTO requestDTO = objectMapper.readValue(input.getBody(), LoanCalculatorRequestDTO.class);
            LoanCalculatorResponseDTO result = calculatorCommand.execute(requestDTO);
            String responseBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

            return response.withStatusCode(200).withBody(responseBody);
        } catch (CalculatorValidationException e) {
            var errors = e.getValidationResult().getErrors();
            context.getLogger().log("Validation error: " + e.getMessage());
            try {
                return response.withStatusCode(400)
                        .withBody(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(errors));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Processing error", e);
        }
    }
}