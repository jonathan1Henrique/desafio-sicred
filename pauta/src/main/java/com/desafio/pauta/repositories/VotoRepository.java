package com.desafio.pauta.repositories;

import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findByAssociadoCpf(String cpf);

	@Query("select count (voto) from Voto where pauta = ?1 and voto = ?2")
	Long totalVotos(Pauta pauta, boolean voto);

}
