package com.desafio.pauta.repositories;

import com.desafio.pauta.entities.Pauta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PautaRepository extends PagingAndSortingRepository<Pauta, Long> {

}
