package com.flight.exercise.flight.excercise.controllers;

import com.flight.exercise.flight.excercise.responses.AvailableTicketResponse;
import com.flight.exercise.flight.excercise.responses.HttpExceptionResponse;
import com.flight.exercise.flight.excercise.services.ValidateTicketExistsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/flight/tickets")
@Slf4j
public class TicketsController {

    @Autowired
    private ValidateTicketExistsService validateCouponService;

    public TicketsController(ValidateTicketExistsService validateCouponService) {
        this.validateCouponService = validateCouponService;
    }

    @GetMapping(path = "/{ticket_id}", produces = "application/json")
    public ResponseEntity checkTicketAvailable(@PathVariable("ticket_id") Integer ticketId) {
        try {
            log.info(":checkTicketAvailable: validating if ticket is available, ticket id - " + ticketId);
            Boolean response = validateCouponService.validateTicketAvailable(ticketId);
            log.info(":checkTicketAvailable: ticket id - " + ticketId + ", is available - " + response);
            return ResponseEntity.ok(AvailableTicketResponse.builder().ticketAvailable(response).build());
        } catch (Exception e) {
            log.error(":checkTicketAvailable: unable to validate if ticket is available, ticket id - " + ticketId, e);
            return ResponseEntity.status(500).body(HttpExceptionResponse.builder().errMsg(e.getMessage()).build());
        }
    }
}
