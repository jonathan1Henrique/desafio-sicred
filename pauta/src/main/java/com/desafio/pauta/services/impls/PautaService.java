package com.desafio.pauta.services.impls;


import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.message.producer.IVotoProducer;
import com.desafio.pauta.repositories.PautaRepository;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResponseDTO;
import com.desafio.pauta.services.IPautaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PautaService implements IPautaService {

	@Autowired
	private PautaRepository repository;

	@Override
	public Pauta create(PautaDTO dto) {
		Pauta pauta = new Pauta();
		pauta.setNome(dto.getNome());
		pauta.setDescricao(dto.getDescricao());

		return repository.save(pauta);
	}

	@Override
	public ResponseDTO start(Long idPauta, Date dataInicio) {

		Pauta pauta = getIfExist(idPauta);

		pauta.setData(validaData(dataInicio));

		repository.save(pauta);

		return new ResponseDTO().responseCreatePauta(pauta.getNome(), pauta.getData());
	}

	@Override
	public Page<Pauta> findAll(Pageable page) {

		return repository.findAll(page);
	}

	private Date validaData(Date data) {

		if (data == null) {
			return adicionaTempoPadrao();
		}

		if (data.before(new Date())) {
			throw new ServiceException("Data inválida, informe uma data futura, para iniciar uma votação");
		}

		return data;
	}

	public Pauta getIfExist(Long idPauta){
		return repository.findById(idPauta)
				.orElseThrow(()-> new ServiceException("Não existe uma pauta com o ID " + idPauta));

	}

	private Date adicionaTempoPadrao() {
		Calendar novaData = Calendar.getInstance();
		novaData.add(Calendar.MINUTE, 1);
		return novaData.getTime();
	}

}
