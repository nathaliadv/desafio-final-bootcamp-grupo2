package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class DontMatchesException extends RuntimeException {

    private static final long serialVersionUID = -4993205001650484387L;

    public DontMatchesException(String message) {
        super(message);
    }
}
