package br.com.findzipcode.service;

import br.com.findzipcode.model.Address;

import java.util.Optional;

/**
 * Created by Vinicius on 03/12/15.
 */
public interface AddressService {

    /**
     * Busca um Address existente a partir de um CEP informado, caso o Address não seja encontrado a partir deste CEP
     * Uma busca mais profunda será realizada aplicando a regra de substituição do dígitos da direita para a esquerda por zero
     * @param zipcode o cep do Address a ser buscado
     * @return um Optional com o Address encontrado
     */
    Optional<Address> findAddressByZipcode(final String zipcode);

    /**
     * Cria um novo Address
     * @param address Address a ser criado
     */
    void createAddress(final Address address);

    /**
     * Busca um Address existente a partir de um Id informado
     * @param id Id do Address a ser buscado
     * @return um Optional com o Address encontrado
     */
    Optional<Address> findAddressById(final Long id);

    /**
     * Atualiza um Address existente
     * @param address Address a ser atualizado
     */
    void updateAddress(final Address address);

    /**
     * Remove um Address a partir de um Id informado
     * @param id Id do Address a ser removido
     */
    void deleteAddressById(final Long id);

}
