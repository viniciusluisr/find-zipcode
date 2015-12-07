package br.com.findzipcode.service;

import br.com.findzipcode.model.Address;

import java.util.Optional;

/**
 * Created by Vinicius on 03/12/15.
 */
public interface NearestAddressService {

    /**
     * Responsável por realizar uma busca mais profunda será realizada aplicando a regra de substituição do dígitos da direita para a esquerda por zero
     * que posteriormente será utilizada pelo método findAddressByZipcode da classe AddressService
     * @param zipcode o CEP a ser buscadi
     * @return retorna um Optional do Address encontrado
     */
    Optional<Address> findZipcodeInDepth(String zipcode);
}
