package br.com.findzipcode.service;

import br.com.findzipcode.model.Address;

import java.util.Optional;

/**
 * Created by Vinicius on 03/12/15.
 */
public interface NearestAddressService {

    Optional<Address> findZipcodeInDepth(String zipcode);
}