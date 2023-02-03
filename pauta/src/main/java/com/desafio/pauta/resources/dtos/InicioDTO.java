package com.desafio.pauta.resources.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class InicioDTO {
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3", shape = JsonFormat.Shape.STRING)
	private Date dataInicio;

}
