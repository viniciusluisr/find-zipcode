package br.com.findzipcode.service;

import br.com.findzipcode.exception.AddressNotFoundException;
import br.com.findzipcode.model.Address;
import br.com.findzipcode.repository.AddressRepository;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Vinicius on 03/12/15.
 */
@Service
public class AddressServiceImpl implements  AddressService {

    private Logger log = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private NearestAddressService nearestAddressService;
    @Autowired
    private AddressRepository repository;
    private Address nextAddress;

    @Override
    public Optional<Address> findAddressByZipcode(String zipcode) {

        Preconditions.checkArgument(zipcode.matches("\\d{8}"), "CEP inválido!");

        log.info("Buscando um endereço a partir do CEP: " + zipcode);

        Optional<Address> found = nearestAddressService.findZipcodeInDepth(zipcode);

        if(!found.isPresent())
            throw new AddressNotFoundException("Endereço não encontrado, por favor, verifique o CEP informado: " + zipcode);

        return found;

    }

}
