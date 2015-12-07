package br.com.findzipcode.service;

import br.com.findzipcode.exception.AddressNotFoundException;
import br.com.findzipcode.model.Address;
import br.com.findzipcode.repository.AddressRepository;
import br.com.findzipcode.test.support.TestFixtureSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

/**
 * Created by Vinicius on 04/12/15.
 */
@RunWith(JUnit4.class)
public class AddressServiceTest extends TestFixtureSupport {

    @Mock
    private AddressRepository repository;
    @InjectMocks
    private AddressService addressService = new AddressServiceImpl();
    @Mock
    private NearestAddressService nearestAddressService;
    private Optional<Address> expected;

    @Override
    public void setUp() {
        expected = Optional.of(getExpectedAddress());
        when(nearestAddressService.findZipcodeInDepth(any(String.class))).thenReturn(expected);
    }

    @Test
    public void testCreateAddress() {
        Address address = getExpectedAddressWithoutId();
        addressService.createAddress(address);

        when(repository.findByZipcode(any(String.class))).thenReturn(expected.get());
        when(repository.findOne(any(Long.class))).thenReturn(expected.get());

        assertThat(expected, equalTo(addressService.findAddressById(expected.get().getId())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAddressWithInvalidZipcode() {
        Address address = getExpectedAddressWithoutId();
        address.setZipcode("9999999999");

        addressService.createAddress(address);

        when(repository.findByZipcode(any(String.class))).thenReturn(address);
        when(repository.findOne(any(Long.class))).thenReturn(address);
    }

    @Test
    public void testFindAddressById() {
        when(repository.findOne(any(Long.class))).thenReturn(expected.get());
        Optional<Address> address = addressService.findAddressById(expected.get().getId());
        assertThat(expected, equalTo(address));
    }

    @Test(expected = AddressNotFoundException.class)
    public void testFindAddressByIdWithNoExistingId() {
        addressService.findAddressById(9999L);
    }

    @Test
    public void testUpdateAddress() {
        when(repository.findOne(any(Long.class))).thenReturn(expected.get());
        addressService.updateAddress(expected.get());
    }

    @Test(expected = AddressNotFoundException.class)
    public void testUpdateAddressWithNoExistingAddress() {
        addressService.updateAddress(getExpectedAddress());
    }

    @Test
    public void deleteAddressById() {
        when(repository.findOne(any(Long.class))).thenReturn(expected.get());
        addressService.deleteAddressById(expected.get().getId());
    }

    @Test(expected = AddressNotFoundException.class)
    public void deleteAddressByIdWithNoExistingId() {
        addressService.deleteAddressById(getExpectedAddress().getId());
    }

    private Address getExpectedAddress() {
        Optional<Address> address = Optional.of(Fixture.from(Address.class).gimme("valid"));
        return address.get();
    }

    private Address getExpectedAddressWithoutId() {
        Optional<Address> address = Optional.of(Fixture.from(Address.class).gimme("validWithoutId"));
        return address.get();
    }

}