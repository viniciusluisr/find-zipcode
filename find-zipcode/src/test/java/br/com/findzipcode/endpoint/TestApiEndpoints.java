package br.com.findzipcode.endpoint;

import br.com.findzipcode.model.Address;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

/**
 * Classe responsável por disponibilizar os endpoints da api para a realização de testes de integração
 * Created by Vinicius on 06/12/15.
 */
public class TestApiEndpoints {

    private static final String ZIPCODE_ENDPOINT = "http://127.0.0.1:8081/v1/zipcodes";
    private static final String ADDRESS_ENDPOINT = "http://127.0.0.1:8081/v1/addresses";

    public static ResponseEntity<Address> findAddressByZipcode(final String zipcode) {
        return new TestRestTemplate().getForEntity(ZIPCODE_ENDPOINT + "/" + zipcode, Address.class);
    }

    public static ResponseEntity<Address> createAddress(final Address address) {
        return new TestRestTemplate().postForEntity(ADDRESS_ENDPOINT, address, Address.class, Collections.EMPTY_MAP);
    }

    public static ResponseEntity<Address> findAddressById(final Long id) {
        return new TestRestTemplate().getForEntity(ADDRESS_ENDPOINT + "/" + id, Address.class);
    }

    public static void updateAddress(final Address address) {
        new TestRestTemplate().put(ADDRESS_ENDPOINT, address, Address.class, Collections.EMPTY_MAP);
    }

    public static void deleteAddressById(final Long id) {
        new TestRestTemplate().delete(ADDRESS_ENDPOINT + "/" + id, Address.class);
    }


}
