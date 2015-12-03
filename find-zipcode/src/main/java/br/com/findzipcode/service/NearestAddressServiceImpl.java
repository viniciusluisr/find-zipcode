package br.com.findzipcode.service;

import br.com.findzipcode.model.Address;
import br.com.findzipcode.repository.AddressRepository;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Created by Vinicius on 03/12/15.
 */
@Service
public class NearestAddressServiceImpl implements NearestAddressService {

    private Logger log = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository repository;
    private Address nextAddress;

    @Override
    public Optional<Address> findZipcodeInDepth(String zipcode) {
        Address address = repository.findByZipcode(zipcode);

        if(address != null) return Optional.of(address);

        log.info("Buscando CEPs próximos...");


        new NearestZipcodes(zipcode).forEach(next -> {
            if(nextAddress == null) {
                log.info("Buscando CEPs próximos ao: " + next);

                nextAddress = repository.findByZipcode(next);
            }
        });

        return nextAddress != null ? Optional.of(nextAddress) : Optional.empty();

    }

    public class NearestZipcodes implements Iterable<String> {

        @Getter
        private List<String> zipcodes = new ArrayList<>();

        public NearestZipcodes(String zipcode) {
            getNearestZipcodes(zipcode);
        }

        @Override
        public Iterator<String> iterator() {
            return this.zipcodes.iterator();
        }

        /*
         * Carrega os CEPs próximos aplicando a regra de substituição dos dígitos por zero
         */
        private void getNearestZipcodes(String zipcode) {
            for (int i = zipcode.length() - 1; i > 0; i--) {
                String nearZipcode = zipcode.substring(0, i) + '0' + zipcode.substring(i + 1);
                zipcode = nearZipcode;

                zipcodes.add(zipcode);
            }

        }
    }
}
