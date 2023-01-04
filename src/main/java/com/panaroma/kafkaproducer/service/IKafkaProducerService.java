package com.panaroma.kafkaproducer.service;

import com.panaroma.kafkaproducer.dto.MessageDTO;
import com.panaroma.kafkaproducer.dto.MessagePushedSuccessDTO;

public interface IKafkaProducerService {

    public abstract MessagePushedSuccessDTO pushName (MessageDTO messageDTO);

}
