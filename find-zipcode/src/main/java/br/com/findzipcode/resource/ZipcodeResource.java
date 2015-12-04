package br.com.findzipcode.resource;

import br.com.findzipcode.model.Address;
import br.com.findzipcode.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Endpoint responsável por tratar requests referentes ao domínio CEP
 * Created by Vinicius on 03/12/15.
 */
@RestController
@RequestMapping(value = "/v1/cep")
@Validated
public class ZipcodeResource {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> getAddressByZipcode(@PathVariable final String cep) {
        Optional<Address> response = addressService.findAddressByZipcode(cep);
        return new ResponseEntity<Address>(response.get(), HttpStatus.OK);
    }

}
