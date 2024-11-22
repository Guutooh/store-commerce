package br.com.dev.ecommerce.exceptions.handlers;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class ValidationError extends CustomError{

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String field, String message) {

        errors.removeIf(x -> x.getFieldName().equals(field));

        errors.add(new FieldMessage(field, message));
    }

}
