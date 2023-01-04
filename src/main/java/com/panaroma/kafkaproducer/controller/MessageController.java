package com.panaroma.kafkaproducer.controller;

import com.panaroma.kafkaproducer.dto.MessagePushedSuccessDTO;
import com.panaroma.kafkaproducer.dto.MessageDTO;
import com.panaroma.kafkaproducer.service.IKafkaProducerService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Kafka Producer Message Endpoints
 * */
@RestController
@RequestMapping("/produce")
@AllArgsConstructor
public class MessageController {

    private IKafkaProducerService producerService;

    @PostMapping(path = "/name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessagePushedSuccessDTO> pushMessage(@RequestBody @NotNull MessageDTO message) {

        MessagePushedSuccessDTO successMessage = producerService.pushName(message);
        successMessage.add(linkTo(methodOn(MessageController.class).pushMessage(message)).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);

    }

}
