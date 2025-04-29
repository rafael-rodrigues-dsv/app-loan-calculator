package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.PaymentTypeEnum;
import br.com.example.domain.calculator.enumeration.TaxTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxRequestDTO {
    private PaymentTypeEnum paymentType;
    private BigDecimal value;
    private TaxTypeEnum taxType;
}
