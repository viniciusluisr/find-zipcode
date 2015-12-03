package br.com.findzipcode.service;

import br.com.findzipcode.exception.AddressNotFoundException;
import br.com.findzipcode.model.Address;
import br.com.findzipcode.repository.AddressRepository;
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
    private AddressRepository repository;
    private Address nextAddress;

    @Override
    public Optional<Address> findAddressByZipcode(String zipcode) {

        log.info("Buscando um endereço a partir do CEP: " + zipcode);

        Optional<Address> found = findZipcodeInDepth(zipcode);

        if(!found.isPresent())
            throw new AddressNotFoundException("Endereço não encontrado, por favor, verifique o CEP informado: " + zipcode);

        return found;

    }


    private List<String> getNearestZipcodes(String zipcode) {
        List<String> zipcodes = new ArrayList<>();

        for (int i = zipcode.length() - 1; i > 0; i--) {
            String nearZipcode = zipcode.substring(0, i) + '0' + zipcode.substring(i + 1);
            zipcode = nearZipcode;

            zipcodes.add(zipcode);
        }

        return zipcodes;

    }

    @Override
    public Optional<Address> findZipcodeInDepth(String zipcode) {
        Address address = repository.findByZipcode(zipcode);

        if(zipcode != null) return Optional.of(address);

        log.info("Buscando CEPs próximos...");

        getNearestZipcodes(zipcode).forEach(next -> {
            if(nextAddress == null) {
                log.info("Buscando CEPs próximos ao: " + next);

                nextAddress = repository.findByZipcode(next);
            }
        });

        return nextAddress != null ? Optional.of(nextAddress) : Optional.empty();

    }

}
