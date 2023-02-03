package com.desafio.pauta.services.impls;

import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.repositories.AssociadoRepository;
import com.desafio.pauta.resources.dtos.AssociadoDTO;
import com.desafio.pauta.services.IAssociadoService;
import com.desafio.pauta.services.ICPFService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService implements IAssociadoService {
	
	@Autowired
	private AssociadoRepository repository;

	@Autowired
	private ICPFService cpfService;

	@Override
	public Associado create(AssociadoDTO dto) {

        Optional<Associado> associado = repository.findById(dto.getCpf());

		if(associado.isEmpty()){
			cpfService.validar(dto.getCpf());
		    return repository.save(new Associado(dto.getCpf(), dto.getNome()));
        }
		throw new ServiceException("CPF " + dto.getCpf() + " já cadastrado na base" );
	}

	@Override
	public Associado getIfExist(String cpf) {
		return repository.findById(cpf)
				.orElseThrow(()-> new ServiceException("Associado não cadastrado, favor cadastrar associado"));
	}


}
