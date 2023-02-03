package com.desafio.pauta.services;

import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.resources.dtos.AssociadoDTO;

public interface IAssociadoService {

   Associado create(AssociadoDTO dtoo);

   Associado getIfExist(String cpf);
}
