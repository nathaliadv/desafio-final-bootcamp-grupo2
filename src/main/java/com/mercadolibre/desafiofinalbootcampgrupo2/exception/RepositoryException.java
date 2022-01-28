package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class RepositoryException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public RepositoryException(String message) {
        super(message);
    }
}
