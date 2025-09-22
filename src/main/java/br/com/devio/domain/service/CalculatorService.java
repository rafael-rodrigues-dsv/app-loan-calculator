package br.com.devio.domain.service;

import br.com.devio.generated.dto.SimulationRequestDTO;
import br.com.devio.generated.dto.SimulationResponseDTO;

public interface CalculatorService {

    SimulationResponseDTO executeSimulation(SimulationRequestDTO loanCalculatorRequestDto);
}
