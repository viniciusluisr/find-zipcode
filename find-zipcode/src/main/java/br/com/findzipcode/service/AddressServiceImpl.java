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

import static br.com.findzipcode.util.ValidateUtils.notNullParameter;

/**
 * Created by Vinicius on 03/12/15.
 */
@Service
public class AddressServiceImpl implements  AddressService {

    private Logger log = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private NearestAddressService nearestAddressService;
    @Autowired
    private AddressRepository addressRepository;
    private Address nextAddress;

    @Override
    public Optional<Address> findAddressByZipcode(final String zipcode) {
        Preconditions.checkArgument(zipcode.matches("\\d{8}"), "CEP inválido!");

        log.info("Buscando um endereço a partir do CEP: " + zipcode);

        Optional<Address> found = nearestAddressService.findZipcodeInDepth(zipcode);

        if(!found.isPresent())
            throw new AddressNotFoundException("Endereço não encontrado, por favor, verifique o CEP informado: " + zipcode);

        return found;

    }

    @Override
    public void createAddress(final Address address) {
        notNullParameter(address, "address");
        Preconditions.checkArgument(address.getZipcode().matches("\\d{8}"), "CEP inválido!");

        if(addressRepository.findByZipcode(address.getZipcode()) == null) {
            log.info("Salvando o endereço: " + address.toString());
            addressRepository.save(address);
        }
    }

    @Override
    public Optional<Address> findAddressById(final Long id) {
        notNullParameter(id, "id");

        log.info("Buscando endereço a partir do Id: " + id);
        Address address = addressRepository.findOne(id);

        if(address == null) throw new AddressNotFoundException("Endereço não encontrado, por favor, verifique o Id informado: " + id);

        return Optional.of(address);
    }

    @Override
    public void updateAddress(final Address address) {
        notNullParameter(address, "address");
        Preconditions.checkArgument(address.getZipcode().matches("\\d{8}"), "CEP inválido!");

        if(addressRepository.findOne(address.getId()) !=  null) {
            log.info("Atualizando endereço com os dados: " + address.toString());
            addressRepository.save(address);
        } else
            throw new AddressNotFoundException("Endereço não encontrado, por favor, verifique os dados informados: " + address.toString());

    }

    @Override
    public void deleteAddressById(final Long id) {
        notNullParameter(id, "id");

        if(findAddressById(id).isPresent()) {
            log.info("Excluindo um endereço com o Id: " + id);
            addressRepository.delete(id);
        }
    }

}
