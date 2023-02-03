package com.desafio.pauta.resources.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultadoVotoDTO {

	private PautaDTO pauta;
	private Long totalNao;
	private Long totalSim;
	private Long total;
}
