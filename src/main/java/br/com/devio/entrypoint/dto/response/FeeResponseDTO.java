package br.com.devio.entrypoint.dto.response;

import br.com.devio.domain.enumeration.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeResponseDTO {
    private PaymentTypeEnum paymentType;
    private BigDecimal totalAmount;
}
