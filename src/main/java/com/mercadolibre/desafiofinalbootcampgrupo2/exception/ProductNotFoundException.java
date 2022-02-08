package com.mercadolibre.desafiofinalbootcampgrupo2.exception;

public class ProductNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -4993205001650484387L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
