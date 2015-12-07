package br.com.findzipcode.endpoint;

import br.com.findzipcode.Application;
import br.com.findzipcode.model.Address;
import br.com.findzipcode.test.support.TestFixtureSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Vinicius on 06/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port=8081")
public class AddressResourceTest extends TestFixtureSupport {

    private Address address = Fixture.from(Address.class).gimme("validWithoutId");

    @Override
    public void setUp() {
        getExpectedAddress();
    }

    @Test
    public void testCreateAddress() {
       ResponseEntity<Address> responseEntity = TestApiEndpoints.createAddress(address);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateAddressWithInvalidZipcode() throws Exception {
        address.setZipcode("77777777777");
        ResponseEntity<Address> responseEntity = TestApiEndpoints.createAddress(address);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateAddressWithInvalidFields() throws Exception {
        Address invalid = new Address();
        invalid.setZipcode("99999999");
        ResponseEntity<Address> responseEntity = TestApiEndpoints.createAddress(invalid);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testFindAddressById() {
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressById(getExpectedAddress().getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFindAddressByIdWithNoExistingId() {
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressById(300l);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateAddress() {
        Address updated = getExpectedAddress();
        updated.setZipcode("89896565");
        updated.setStreet("Avenida Duque de Valtomer");

        TestApiEndpoints.updateAddress(updated);

        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressById(updated.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updated, responseEntity.getBody());
    }

    @Test
    public void testUpdateAddressWithNoExistingAddress() throws  Exception {
        Address invalid = Fixture.from(Address.class).gimme("valid");
        TestApiEndpoints.updateAddress(invalid);
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressById(invalid.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAddressById() {
        Address deleted = getExpectedAddress();
        TestApiEndpoints.deleteAddressById(deleted.getId());

        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressById(deleted.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    public Address getExpectedAddress() {
        return TestApiEndpoints.createAddress(address).getBody();
    }

}


