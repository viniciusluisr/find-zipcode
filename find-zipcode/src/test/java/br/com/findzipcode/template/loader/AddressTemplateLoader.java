package br.com.findzipcode.template.loader;

import br.com.findzipcode.model.Address;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * Created by Vinicius on 04/12/15.
 */
public class AddressTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Address.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Long.class, range(1L, 200L)));
                add("zipcode", random("83838383", "08556330", "08556300", "08556000", "08550000", "08500000", "08000000", "00000000"));
                add("number", random(106, 205, 303, 402, 501));
                add("street", random("Rua Barão Vila Lobos", "Estrada Zumbi dos Palmares", "Avenida das Nações Unidas", "Rua Dom Bosco", "Avenida do Espírito Santo"));
                add("neighborhood", random("Vila Lobos", "Santa Elena", "Berrini", "Jardins", "Consolação"));
                add("city", "São Paulo");
                add("state", "SP");
                add("complement", random("Fundos", "Casa 01", "Casa 02", "3 andar", "Térreo"));
            }
        });

        Fixture.of(Address.class).addTemplate("validWithoutId", new Rule() {
            {
                add("zipcode", random("83838383", "08556330", "08556300", "08556000", "08550000", "08500000", "08000000", "00000000"));
                add("number", random(106, 205, 303, 402, 501));
                add("street", random("Rua Barão Vila Lobos", "Estrada Zumbi dos Palmares", "Avenida das Nações Unidas", "Rua Dom Bosco", "Avenida do Espírito Santo"));
                add("neighborhood", random("Vila Lobos", "Santa Elena", "Berrini", "Jardins", "Consolação"));
                add("city", "São Paulo");
                add("state", "SP");
                add("complement", random("Fundos", "Casa 01", "Casa 02", "3 andar", "Térreo"));
            }
        });
    }
}

