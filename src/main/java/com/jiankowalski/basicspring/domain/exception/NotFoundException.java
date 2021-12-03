package com.jiankowalski.basicspring.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, Long id) {
        super(String.format("Record not found for %s with id %d", entity, id));
    }
}
