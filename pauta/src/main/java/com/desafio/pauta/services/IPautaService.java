package com.desafio.pauta.services;


import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Date;

public interface IPautaService {

    Pauta create(PautaDTO dto);

    ResponseDTO start(Long idPauta, Date dataInicio);

    Page<Pauta> findAll(Pageable page);

    Pauta getIfExist(Long idPauta);

}
