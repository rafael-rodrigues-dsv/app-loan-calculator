package br.com.devio.component.calculator.command.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MathCalculationPowerInput {
    private final BigDecimal base;
    private final BigDecimal exponent;
}
