package com.desafio.pauta.message.consumer.impls;


import com.desafio.pauta.message.consumer.IVotoConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class VotoConsumer implements IVotoConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(VotoConsumer.class);

	@Override
	public void consumer(String votoStr) {

		log.info("Voto enviado com sucesso: " + votoStr);
	}
}
