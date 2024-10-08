package br.com.dev.ecommerce.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}
