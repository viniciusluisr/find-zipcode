package br.com.findzipcode.resource;

import br.com.findzipcode.model.Address;
import br.com.findzipcode.service.AddressService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
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

import java.util.Optional;

/**
 * Endpoint responsável por tratar requests referentes ao domínio CEP
 * Created by Vinicius on 03/12/15.
 */
@RestController
@RequestMapping(value = "/v1/zipcodes")
@Validated
@Api(value = "API de CEP", description = "Essa API tem como objetivo buscar endereços existentes a partir de um CEP informado", basePath = "/v1/zipcodes", produces = "application/json")
public class ZipcodeResource {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Busca um Endereço existente a partir de um CEP informado (deve conter 8 digitos numéricos)", notes = "Retorna um objeto Address", response = Address.class)
    @RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> getAddressByZipcode(@PathVariable final String cep) {
        Optional<Address> response = addressService.findAddressByZipcode(cep);
        return new ResponseEntity<Address>(response.get(), HttpStatus.OK);
    }

}
