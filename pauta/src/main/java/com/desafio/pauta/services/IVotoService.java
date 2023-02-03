package com.desafio.pauta.services;


import com.desafio.pauta.resources.dtos.ResultadoVotoDTO;
import com.desafio.pauta.resources.dtos.VotoDTO;

public interface IVotoService {

    void votar(VotoDTO voto);

    ResultadoVotoDTO resultado(Long idPauta);
}
