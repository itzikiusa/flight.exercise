package com.flight.exercise.flight.excercise.controllers;

import com.flight.exercise.flight.excercise.responses.BaggageCheckedInResponse;
import com.flight.exercise.flight.excercise.responses.HttpExceptionResponse;
import com.flight.exercise.flight.excercise.services.ValidateBaggageCheckedInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/flight/baggage")
@Slf4j
public class BaggageController {

    @Autowired
    private ValidateBaggageCheckedInService validateBaggageCheckedInService;

    public BaggageController(ValidateBaggageCheckedInService validateBaggageCheckedInService) {
        this.validateBaggageCheckedInService = validateBaggageCheckedInService;
    }

    @GetMapping(path = "/{destination_id}/{baggage_id}", produces = "application/json")
    public ResponseEntity checkTicketAvailable(@PathVariable("destination_id") Integer destinationId, @PathVariable("baggage_id") String baggageId) {
        try {
            log.info(":checkTicketAvailable: validating if baggage checked in for destination - " + destinationId + ", baggage id - " + baggageId);
            Boolean response = validateBaggageCheckedInService.validateBaggeCheckedIn(destinationId, baggageId);
            log.info(":checkTicketAvailable: baggage - " + baggageId + ", checked in - " + response + ", for destination id - " + destinationId);
            return ResponseEntity.ok(BaggageCheckedInResponse.builder().checkedIn(response).build());
        } catch (Exception e) {
            log.info(":checkTicketAvailable: unable tp validate if baggage checked in for destination - " + destinationId + ", baggage id - " + baggageId, e);
            return ResponseEntity.status(500).body(HttpExceptionResponse.builder().errMsg(e.getMessage()).build());
        }
    }
}
