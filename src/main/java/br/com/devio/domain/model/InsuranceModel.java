package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.PaymentTypeEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceModel {
    private PaymentTypeEnum paymentType;
    private AmountModel totalAmount;
}
