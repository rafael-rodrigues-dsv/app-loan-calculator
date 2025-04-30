package br.com.example.domain.calculator.model;

import br.com.example.domain.calculator.enumeration.PaymentTypeEnum;
import br.com.example.domain.calculator.enumeration.TaxTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
