package com.example.backendsigninpractice.exception;



public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id,Class<?> entity){
        super(String.format("the %s with id %d was not found",entity.getSimpleName(),id));
    }
}
