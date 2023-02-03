package com.desafio.pauta.services.impls;

import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.entities.Voto;
import com.desafio.pauta.message.producer.IVotoProducer;
import com.desafio.pauta.repositories.VotoRepository;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResultadoVotoDTO;
import com.desafio.pauta.resources.dtos.VotoDTO;
import com.desafio.pauta.services.IAssociadoService;
import com.desafio.pauta.services.IPautaService;
import com.desafio.pauta.services.IVotoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotoService implements IVotoService {

	@Autowired
    private VotoRepository repository;
    
    @Autowired
	private IPautaService pautaService;
    
    @Autowired
	private IAssociadoService associadoService;

    @Autowired
	private IVotoProducer votoProducer;

	public void votar(VotoDTO dto) {

		associadoService.getIfExist(dto.getCpf());

    	Pauta pauta = pautaService.getIfExist(dto.getIdPauta());

		validaSecaoVoto(pauta.getData());

		existenteVoto(dto.getCpf());

		salvar(dto);

		votoProducer.send(dto.tranfomeJson());
    }

	@Override
	public ResultadoVotoDTO resultado(Long idPauta) {

		Pauta pauta = pautaService.getIfExist(idPauta);

		Long totalSim = repository.totalVotos(pauta, Boolean.TRUE);
		Long totalNao = repository.totalVotos(pauta, Boolean.FALSE);

		ResultadoVotoDTO dto = new ResultadoVotoDTO();

		dto.setPauta(new PautaDTO());
		dto.getPauta().setNome(pauta.getNome());
		dto.getPauta().setDescricao(pauta.getDescricao());
		dto.setTotal(totalSim + totalNao);
		dto.setTotalNao(totalNao);
		dto.setTotalSim(totalSim);

		return dto;
	}

	private void salvar(VotoDTO voto) {

		Pauta pauta = pautaService.getIfExist(voto.getIdPauta());

		validaSecaoVoto(pauta.getData());

		Associado associado = associadoService.getIfExist(voto.getCpf());

		repository.save(
				new Voto(null,
						votoStringToBoolean(voto.getVoto()),
						associado,
						pauta)
		);
	}

	private void validaSecaoVoto(Date data) {
		if (data==null) {
			throw new ServiceException("Votação não iniciada");
		}

		if (data.before(new Date())) {
			throw new ServiceException("Votações encerradas");
		}
	}

	private void existenteVoto(String cpf) {
		if(!repository.findByAssociadoCpf(cpf).isEmpty()){
			throw new ServiceException("Voto já foi computado");
		}
	}

	private boolean votoStringToBoolean(String voto) {
    	voto = voto.toLowerCase();
    	if(voto.equals("sim") || voto.equals("s")){
    		return true;
		}
		if(voto.equals("nao") || voto.equals("n") || voto.equals("não")){
			return false;
		}
		throw new ServiceException("O voto deve ser Sim(S) ou Não(N).");

	}

}