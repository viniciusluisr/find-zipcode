package br.com.findzipcode.repository;

import br.com.findzipcode.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Vinicius on 03/12/15.
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

    List<Address> findByZipcode(String zipcode);

}
