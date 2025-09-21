package br.com.devio.entrypoint.resource;

import br.com.devio.domain.service.CalculatorService;
import br.com.devio.generated.api.SimulationsApi;
import br.com.devio.generated.dto.SimulationRequestDTO;
import br.com.devio.generated.dto.SimulationResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CalculatorResource implements SimulationsApi {

    @Inject
    CalculatorService simulationService;

    @Override
    public SimulationResponseDTO simulateLoan(SimulationRequestDTO request) {
        return simulationService.executeSimulation(request);
    }
}