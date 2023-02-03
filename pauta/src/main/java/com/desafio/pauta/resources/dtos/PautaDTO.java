package com.desafio.pauta.resources.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

	@NotEmpty(message = "Favor informar o nome da Pauta")
	@Length(max = 80,message = "O nome deverá ter no máximo {max} caracteres")
	private String nome;

	@Length(max = 200, message = "A descrição deverá ter no máximo {max} caracteres")
	private String descricao;


}
