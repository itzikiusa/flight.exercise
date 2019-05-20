package com.flight.exercise.flight.excercise.dao.impl;

import com.flight.exercise.flight.excercise.dao.ValidateTicketExistsDao;
import com.flight.exercise.flight.excercise.db.AvailableTicketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateTicketExistsDaoImpl implements ValidateTicketExistsDao {

    @Autowired
    private final AvailableTicketDb availableTicketDb;

    public ValidateTicketExistsDaoImpl(AvailableTicketDb availableTicketDb) {
        this.availableTicketDb = availableTicketDb;
    }

    @Override
    public boolean validateTicketAvailable(Integer ticketId) {
        return availableTicketDb.validateTicketAvailable(ticketId);
    }
}
