package com.desafio.pauta.message.producer.impls;

import com.desafio.pauta.message.producer.IVotoProducer;
import com.desafio.pauta.resources.dtos.VotoDTO;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VotoProducerImpl implements IVotoProducer {

    private static final Logger log = LoggerFactory.getLogger(VotoProducerImpl.class);

    @Value("${topic.voto}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String voto) {
        Gson gson = new Gson();

        VotoDTO dto = gson.fromJson(voto, VotoDTO.class);

        var record = new ProducerRecord<>(topic, dto.getCpf(), voto);

        kafkaTemplate.send(record);

    }

}
