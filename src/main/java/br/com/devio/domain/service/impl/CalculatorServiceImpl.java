package br.com.devio.domain.service.impl;

import br.com.devio.component.mapper.CustomMapper;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.service.CalculatorService;
import br.com.devio.domain.usecase.SimulationUseCase;
import br.com.devio.generated.dto.SimulationRequestDTO;
import br.com.devio.generated.dto.SimulationResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CalculatorServiceImpl implements CalculatorService {

    @Inject
    private SimulationUseCase simulationUseCase;

    @Inject
    private CustomMapper customMapper;

    @Override
    public SimulationResponseDTO executeSimulation(SimulationRequestDTO loanCalculatorRequestDto) {
        return customMapper.map(simulationUseCase.executeSimulation(customMapper.map(loanCalculatorRequestDto, LoanModel.class)),
                SimulationResponseDTO.class);
    }
}
