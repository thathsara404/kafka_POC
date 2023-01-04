package com.panaroma.kafkaproducer.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessagePushedSuccessDTO extends RepresentationModel implements ISuccessResponse {

    @NotNull
    private String success;

}
