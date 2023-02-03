package com.desafio.pauta.services;


import com.desafio.pauta.entities.Pauta;
import com.desafio.pauta.repositories.PautaRepository;
import com.desafio.pauta.resources.dtos.PautaDTO;
import com.desafio.pauta.resources.dtos.ResponseDTO;
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

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PautaServiceTest {

	@MockBean
	private PautaRepository repository;

	@Autowired
	private IPautaService service;

	@TestConfiguration
	static class PautaServiceTestConfiguration{
		@Bean
		public PautaService pautaService(){
			return new PautaService();
		}
	}


	@Test
	@DisplayName("Cria uma Pauta")
	public void createTest(){
		PautaDTO dto = new PautaDTO("Teste cria pauta", "teses unitários");

		Pauta pauta = Mockito.mock(Pauta.class);

		Mockito.when(repository.save(ArgumentMatchers.any(Pauta.class))).thenReturn(pauta);

		Pauta pautaService = service.create(dto);

		Assertions.assertNotNull(pauta);
		Assertions.assertNotNull(pautaService);

		Assertions.assertEquals(pautaService, pauta);
	}

	@Test
	@DisplayName("Da inicio a uma pauta")
	public void startTest() {

		Optional<Pauta> pauta = Optional.ofNullable(Mockito.mock(Pauta.class));

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(pauta);

		ResponseDTO responseDTO = service.start(1L, null);

		Assertions.assertNotNull(pauta);
		Assertions.assertNotNull(responseDTO);

	}

	@Test
	@DisplayName("Busca pauta por id")
	public void findByIdTest() {

		Optional<Pauta> pauta = Optional.ofNullable(Mockito.mock(Pauta.class));

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(pauta);

		Pauta pautaService = service.getIfExist(1L);

		Assertions.assertNotNull(pauta);
		Assertions.assertNotNull(pautaService);

		Assertions.assertEquals(pautaService, pauta.get());

	}

	@Test
	@DisplayName("Busca pauta por id trazendo a exception")
	public void findByIdErroTest() {

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.empty());

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> service.getIfExist(1L));

		Assertions.assertEquals("Não existe uma pauta com o ID " + 1L, exception.getMessage());

	}

}
