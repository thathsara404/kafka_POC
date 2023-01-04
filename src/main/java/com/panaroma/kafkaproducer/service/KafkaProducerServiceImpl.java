package com.panaroma.kafkaproducer.service;

import com.panaroma.kafkaproducer.consts.MessageKey;
import com.panaroma.kafkaproducer.consts.SuccessMessage;
import com.panaroma.kafkaproducer.dto.MessageDTO;
import com.panaroma.kafkaproducer.dto.MessagePushedSuccessDTO;
import com.panaroma.kafkaproducer.error.UnExpectedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements IKafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${topic.name}")
    private String topicName;

    @Override
    public MessagePushedSuccessDTO pushName(MessageDTO messageDTO) {
        try {
            template.send(topicName, MessageKey.NAME_KEY, messageDTO.getMessageValue());
            return new MessagePushedSuccessDTO(SuccessMessage.NAME_PUSHED_SUCCESSFULLY);
        } catch (Exception exception) {
            throw new UnExpectedError(exception.getMessage(), exception.getCause());
        }
    }

}
