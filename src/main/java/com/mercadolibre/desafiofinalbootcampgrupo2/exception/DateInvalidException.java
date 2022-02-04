package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class DateInvalidException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public DateInvalidException(String message) {
        super(message);
    }
}
