package br.com.dev.ecommerce.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage {

    private String fieldName;
    private String message;

}
