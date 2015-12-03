package br.com.findzipcode.exception;

/**
 * Created by Vinicius on 03/12/15.
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(final String message) {
        super(message);
    }
}
