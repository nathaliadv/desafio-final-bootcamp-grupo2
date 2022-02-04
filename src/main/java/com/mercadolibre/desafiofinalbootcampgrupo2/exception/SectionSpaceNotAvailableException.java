package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class SectionSpaceNotAvailableException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public SectionSpaceNotAvailableException(String message) {
        super(message);
    }
}
