package com.panaroma.kafkaproducer.error;

import com.panaroma.kafkaproducer.consts.ErrorConst;
import com.panaroma.kafkaproducer.dto.ErrorResponseDTO;
import com.panaroma.kafkaproducer.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GenericErrorHandler {

    @ExceptionHandler(UnExpectedError.class)
    public ResponseEntity<ResponseDTO> generateAccountNotFoundResponse(UnExpectedError unExpectedError) {
        ResponseDTO responseDTO = new ResponseDTO(null, new ErrorResponseDTO(ErrorConst.UNEXPECTED_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

}
