package br.com.findzipcode.resource;

import br.com.findzipcode.model.Address;
import br.com.findzipcode.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * Endpoint responsável por tratar requests referentes ao domínio Endereço
 * Created by Vinicius on 04/12/15.
 */
@RestController
@RequestMapping(value = "/v1/addresses")
@Validated
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> createAddress(@RequestBody Address address) {
        addressService.createAddress(address);
        return new ResponseEntity<>(addressService.findAddressByZipcode(address.getZipcode()).get(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> findAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.findAddressById(id);
        return new ResponseEntity<>(address.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> updateAddress(@RequestBody Address address) {
        addressService.updateAddress(address);
        return new ResponseEntity<>(addressService.findAddressByZipcode(address.getZipcode()).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
