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
public class ZipcodeResourceTest extends TestFixtureSupport {

    private  Address address = Fixture.from(Address.class).gimme("validWithoutId");

    @Override
    public void setUp() {
        TestApiEndpoints.createAddress(address);
    }

    @Test
    public void testGetAddressByZipcode() {
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressByZipcode(address.getZipcode());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(address.getZipcode(), responseEntity.getBody().getZipcode());
    }

    @Test
    public void testGetAddressByZipcodeWithInvalidZipcode() throws Exception {
        address.setZipcode("123123123123");
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressByZipcode(address.getZipcode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAddressByZipcodeWithNoExistingZipcode() throws  Exception {
        address.setZipcode("86858483");
        ResponseEntity<Address> responseEntity = TestApiEndpoints.findAddressByZipcode(address.getZipcode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
