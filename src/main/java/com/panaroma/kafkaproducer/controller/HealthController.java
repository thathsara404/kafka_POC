package com.panaroma.kafkaproducer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Health Endpoints
 * */
@RestController
@RequestMapping("/healthCheck")
public class HealthController {

    @GetMapping(value = "/manualHealth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getHealthStatus() {

        return ResponseEntity.status(HttpStatus.OK).body(getHealth());
    }

    public Map<String, String> getHealth() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "running");
        return status;
    }

}
