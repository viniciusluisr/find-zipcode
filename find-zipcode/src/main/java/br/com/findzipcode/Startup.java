package br.com.findzipcode;

import br.com.findzipcode.model.Address;
import br.com.findzipcode.repository.AddressRepository;
import br.com.findzipcode.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por popular o banco em memória no boot da aplicação
 * Created by Vinicius on 03/12/15.
 */
@Component
@Scope("singleton")
public class Startup {

    private Logger log = Logger.getLogger(Startup.class);

    @Autowired
    private AddressRepository addressRepository;

    @PostConstruct
    public void loadData() {
        log.info("Populando o banco de dados com dados iniciais....");


       Address address01 = new Address("83838383", 106, "Rua Barão Vila Lobos", "Vila Lobos", "São Paulo", "SP", "Fundos");
       Address address02 = new Address("98543012", 205, "Estrada Zumbi dos Palmares", "Santa Elena", "São Paulo", "SP", null);
       Address address03 = new Address("78543934", 303, "Avenida das Nações Unidas", "Berrini", "São Paulo", "SP", "12 andar");
       Address address04 = new Address("64598884", 402, "Rua Dom Bosco", "Mariazinha", "Goiânia", "GO", "Casa 03");
       Address address05 = new Address("50492398", 501, "Avenida do Espírito Santo", "Copa Cabana", "Rio de Janeiro", "RJ", "Fundos");

        List<Address> addresses = Arrays.asList(address01,address02,address03,address04,address05);

        addressRepository.save(addresses);

    }
}




