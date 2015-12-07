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
@Api(value = "API de Endereco", description = "Essa API tem como objetivo expor operações CRUD para endereços", basePath = "/v1/addresses", produces = "application/json")
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Cria um endereço", notes = "Cria um endereço e retorna o objeto criado com o Id gerado", response = Address.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> createAddress(@RequestBody Address address) {
        addressService.createAddress(address);
        return new ResponseEntity<>(addressService.findAddressByZipcode(address.getZipcode()).get(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca um Endereço existente a partir de um Id informado", notes = "Retorna um objeto Address", response = Address.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> findAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.findAddressById(id);
        return new ResponseEntity<>(address.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza um Endereço existente", notes = "Atualiza um Endereço existente e retorna o objeto atualizado", response = Address.class)
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Address> updateAddress(@RequestBody Address address) {
        addressService.updateAddress(address);
        return new ResponseEntity<>(addressService.findAddressByZipcode(address.getZipcode()).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Exclui um Endereço existente", notes = "Exclui um Endereço existente", response = Address.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
