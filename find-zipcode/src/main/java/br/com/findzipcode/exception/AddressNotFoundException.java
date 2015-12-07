package br.com.findzipcode.exception;

/**
 * Exception utilizada nos casos onde o Address buscado não existir
 * Created by Vinicius on 03/12/15.
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(final String message) {
        super(message);
    }
}
