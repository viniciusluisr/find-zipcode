package br.com.findzipcode.repository;

import br.com.findzipcode.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vinicius on 03/12/15.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByZipcode(String zipcode);

}
