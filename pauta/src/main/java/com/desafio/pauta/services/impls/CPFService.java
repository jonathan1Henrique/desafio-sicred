package com.desafio.pauta.services.impls;

import com.desafio.pauta.resources.dtos.ResponseDTO;
import com.desafio.pauta.resources.enums.SituacoesCpf;
import com.desafio.pauta.services.ICPFService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CPFService implements ICPFService {

	@Autowired
	private RestTemplate restTemplate;
	 
	@Value("${url.cpf}")
	private String urlServicoCPF;
	
	public void validar(String cpf) {
		
		try {
			ResponseDTO resultado = restTemplate.getForObject(urlServicoCPF.concat("/").concat(cpf),  ResponseDTO.class);

			if(resultado !=null && !resultado.getResponse().equals(SituacoesCpf.ABLE_TO_VOTE.name())){
				throw new ServiceException("CPF inválido.");
			}

		} catch (Exception e) {
			throw new ServiceException("Erro ao validar o CPF.");
		}
	}

}
