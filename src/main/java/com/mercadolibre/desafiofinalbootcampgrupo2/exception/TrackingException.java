package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class TrackingException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public TrackingException(String message) {
        super(message);
    }
}
