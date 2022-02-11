package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class UserInvalidException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public UserInvalidException(String message) {
        super(message);
    }
}