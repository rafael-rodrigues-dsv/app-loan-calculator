package br.com.devio.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountModel {
    private BigDecimal amount;
    
    @Builder.Default
    private String currency = "BRL";
}