package br.com.dev.ecommerce.dto;

import br.com.dev.ecommerce.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private Instant moment;

    public PaymentDTO(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }


}
