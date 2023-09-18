package br.com.example.domain.model;

import br.com.example.domain.enumeration.PaymentTypeEnum;
import br.com.example.domain.enumeration.TaxTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxModel {
    private PaymentTypeEnum paymentType;
    private BigDecimal value;
    private TaxTypeEnum taxType;
}
