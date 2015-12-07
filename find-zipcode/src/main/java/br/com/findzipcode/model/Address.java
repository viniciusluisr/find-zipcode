package br.com.findzipcode.model;

import br.com.findzipcode.model.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Vinicius on 03/12/15.
 */

@Data
@Entity
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Address(String zipcode, Integer number, String street, String neighborhood, String city, String state, String complement) {
        this.zipcode = zipcode;
        this.number = number;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    public Address() {
    }

    @Id
    @GeneratedValue
    private final Long id = null;

    @Size(min = 8, max = 8, message = "O CEP deve conter {min} caracteres numéricos.")
    @NotNull(message = "Por favor, preencha o CEP!")
    @Column(nullable = false, unique = true, length = 8)
    private String zipcode;

    @NotNull(message = "Por favor, preencha o Número!")
    @Column(nullable = false)
    private Integer number;

    @Size(min = 1, max = 180, message = "O Logradouro deve conter no mínimo {min} caracteres e no máximo {max} caracteres.")
    @NotNull(message = "Por favor, preencha o logradouro!")
    @Column(nullable = false, length = 180)
    private String street;

    @Size(max = 200, message = "Bairro deve conter no máximo {max} caracteres.")
    @Column(nullable = false, length = 200)
    private String neighborhood;

    @Size(min = 1, max = 180, message = "Cidade deve conter no mínimo {min} caracteres e no máximo {max} caracteres.")
    @NotNull(message = "Cidade é obrigatório!")
    @Column(nullable = false, length = 180)
    private String city;

    @Size(min = 2, max = 2, message = "Estado deve conter no máximo 2 caracteres.")
    @NotNull(message = "Estado é obrigatório!")
    @Column(nullable = false)
    private String state;

    @Column(nullable = true, length = 120)
    private String complement;

}
