package com.desafio.pauta.resources.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class AssociadoDTO {

    @NotEmpty(message = "Favor informar o CPF do associado")
    @Length(max = 11,message = "O CPF deverá ter no máximo {max} caracteres")
    private String cpf;

    @NotEmpty(message = "Favor informar o nome do associado")
    @Length(max = 80, message = "O nome deve ter no máximo {max} caracteres")
    private String nome;
}
