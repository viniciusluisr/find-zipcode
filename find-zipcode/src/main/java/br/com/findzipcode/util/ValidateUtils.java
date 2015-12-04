package br.com.findzipcode.util;

import java.util.Collection;

/**
 * Classe utilitária para validar e tratar valores nulos
 * Created by Vinicius on 04/12/15.
 */
public class ValidateUtils {

    private ValidateUtils() {
    }

    public static final void notNullParameter(final Object param,
                                              final String paramName) {
        notNull(param, invalidParam(paramName));
    }

    public static final void isNotEmpty(final String param,
                                        final String paramName) {
        if (param == null || param.isEmpty()) {
            throw handleIllegalArgumentException(paramName);
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static final void isNotEmpty(final Collection param,
                                        final String paramName) {
        if (param == null || param.isEmpty()) {
            throw handleIllegalArgumentException(paramName);
        }
    }

    public static final void correctSize(final Collection<?> collection,
                                         final int size,
                                         final String paramName) {
        notEmpty(collection, invalidParam(paramName));
        if (collection.size() != size) {
            throw handleIllegalArgumentException(paramName);
        }
    }

    private static IllegalArgumentException handleIllegalArgumentException(final String paramName) {
        return new IllegalArgumentException(invalidParam(paramName));
    }

    private static String invalidParam(final String paramName) {
        return String.format("%s is invalid.", paramName);
    }

    private static void notNull(final Object param, final String message) {
        if (param == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void notEmpty(final Collection<?> collection,
                                 final String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
