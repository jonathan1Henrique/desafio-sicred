package com.desafio.pauta.services;


import com.desafio.pauta.entities.Associado;
import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.repositories.AssociadoRepository;
import com.desafio.pauta.repositories.PautaRepository;
import com.desafio.pauta.resources.dtos.AssociadoDTO;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResponseDTO;
import com.desafio.pauta.services.impls.AssociadoService;
import com.desafio.pauta.services.impls.PautaService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AssociadoServiceTest {

	@MockBean
	private AssociadoRepository repository;

	@Autowired
	private IAssociadoService service;

	@MockBean
	private ICPFService cpfService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@TestConfiguration
	static class AssociadoServiceTestConfiguration{
		@Bean
		public AssociadoService service(){
			return new AssociadoService();
		}
	}


	@Test
	@DisplayName("Cria uma Pauta")
	public void createTest(){
		AssociadoDTO dto = new AssociadoDTO();

		dto.setCpf("64326269006");
		dto.setNome("Fulano");

		Optional<Associado> associado = Optional.of(Mockito.mock(Associado.class));

		Mockito.when(repository.save(ArgumentMatchers.any(Associado.class))).thenReturn(associado.get());

		Associado associadoService = service.create(dto);

		Assertions.assertNotNull(associadoService);
		Assertions.assertNotNull(associado.get());

		Assertions.assertEquals(associadoService, associado.get());
	}

	@Test
	@DisplayName("Busca associado por id")
	public void findByIdTest() {

		Optional<Associado> associado = Optional.of(Mockito.mock(Associado.class));

		Mockito.when(repository.findById(ArgumentMatchers.eq("64326269006"))).thenReturn(associado);

		Associado associadoService = service.getIfExist("64326269006");

		Assertions.assertNotNull(associadoService);
		Assertions.assertNotNull(associado);
		Assertions.assertEquals(associadoService, associado.get());
	}

	@Test
	@DisplayName("Id não econtrado")
	public void findByIdErroTest() {

		Mockito.when(repository.findById(ArgumentMatchers.eq("64326269006"))).thenReturn(Optional.empty());

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () ->service.getIfExist("64326269006"));

		Assertions.assertEquals("Associado não cadastrado, favor cadastrar associado", exception.getMessage());
	}


	@Test
	@DisplayName("Associado já cadastrado")
	public void createErroTest() {
		AssociadoDTO dto = new AssociadoDTO();

		dto.setCpf("64326269006");
		dto.setNome("Fulano");

		Optional<Associado> associado = Optional.of(Mockito.mock(Associado.class));

		Mockito.when(repository.findById(dto.getCpf())).thenReturn(associado);

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () ->service.create(dto));

		Assertions.assertEquals("CPF " + dto.getCpf() + " já cadastrado na base" , exception.getMessage());
	}

}
