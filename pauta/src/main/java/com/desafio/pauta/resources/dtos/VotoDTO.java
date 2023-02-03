package com.desafio.pauta.resources.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class VotoDTO {

	@NotNull(message = "CPF é obrigatório para votar")
	@NotEmpty(message = "CPF é obrigatório para votar")
	private String cpf;

	@NotEmpty(message = "Nome é obrigatório para votar")
	private String nomeAssociado;

	@NotNull(message = "É obritório informar o identificador da Pauta a ser votada")
	private Long idPauta;

	@NotNull(message = "É obritório informar o identificador da Pauta a ser votada")
	@NotEmpty(message = "É obritório informar o identificador da Pauta a ser votada")
	@Length(max = 3, min = 1, message = "Informe Sim(S) ou Não(N)")
	private String voto;

    public String tranfomeJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
