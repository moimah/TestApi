package com.moimah.ecommerce.utils.exceptions;

/**
 * Custom NotFoundException
 */
public class NotFoundException extends Exception {
    public NotFoundException(Object entity, long id){
        super("Not found exception " + entity  + " with id: " + id + " does not exist");
    }
}
